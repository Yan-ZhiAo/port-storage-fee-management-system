package org.example.sys.service.impl;

import org.example.sys.entity.Instore;
import org.example.sys.entity.Outstore;
import org.example.sys.mapper.OutstoreMapper;
import org.example.sys.service.IOutstoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr.wu
 * @since 2024-06-23
 */
@Service
public class OutstoreServiceImpl extends ServiceImpl<OutstoreMapper, Outstore> implements IOutstoreService {
    @Autowired
    private OutstoreMapper outstoreMapper;

    @Override
    public void calculateAndUpdateOutCost(Outstore outstore) {
        // 根据Outstore的outStoreId获取关联的Instore记录
        List<Instore> relatedInstores = outstoreMapper.findByOutStoreId(outstore.getOutStoreid());

        // 检查是否有相关联的Instore记录
        if (relatedInstores.isEmpty()) {
            throw new NoSuchElementException("No related Instore record found for the provided outStoreId");
        }

        // 将Instore信息转换为模拟InventoryRecord的计算逻辑
        List<InventoryRecord> simulatedInventoryRecords = relatedInstores.stream()
                .map(instore -> new InventoryRecord(
                        instore.getInTime(),
                        instore.getInTon(),
                        outstore.getOutDate(), // 使用出库单上的出库日期
                        outstore.getOutTon(),
                        instore.getProcessedForOutstore(),
                        instore.getInId()))
                .collect(Collectors.toList());

        double storageFee = calculateStorageFee(simulatedInventoryRecords, 14, 0.5);
        outstore.setOutCost(storageFee);
    }

    // 假设的InventoryRecord构造函数，用于模拟计算逻辑
    private static class InventoryRecord {
        private String entryDate;
        private double inTon;
        private String exitDate;
        private double outTon;

        public void setInTon(double inTon) {
            this.inTon = inTon;
        }

        public void setOutTon(double outTon) {
            this.outTon = outTon;
        }

        private  boolean processedForOutstore;

        public boolean isProcessedForOutstore() {
            return processedForOutstore;
        }

        public void setProcessedForOutstore(boolean processedForOutstore) {
            this.processedForOutstore = processedForOutstore;
        }

        public String getEntryDate() {
            return entryDate;
        }

        public double getInTon() {
            return inTon;
        }

        public String getExitDate() {
            return exitDate;
        }

        public InventoryRecord(String entryDate, double tonnage, String exitDate, double outTon, boolean processedForOutstore, String inId) {
            this.entryDate = entryDate;
            this.inTon = tonnage;
            this.exitDate = exitDate;
            this.outTon = outTon;
            this.processedForOutstore = processedForOutstore;
        }

        public double getOutTon() {
            return outTon;
        }
    }

    private double calculateStorageFee(List<InventoryRecord> inventoryRecords, int freeDays, double storageFeePerTonPerDay) {
        double totalFee = 0;

        // 对库存记录按入库日期升序排序，确保先进先出
        inventoryRecords.sort(Comparator.comparing(InventoryRecord::getEntryDate));

        for (InventoryRecord record : inventoryRecords) {
            double outTon = record.getOutTon();

            if (outTon == 0){
                break;
            }

            if (record.isProcessedForOutstore()){
                continue;
            }

            LocalDate entryDateObj;
            try {
                // 解析入库日期
                String entryDateString = record.getEntryDate().split("T")[0];
                entryDateObj = LocalDate.parse(entryDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid entry date format.", e);
            }

            LocalDate exitDateObj;
            try {
                // 解析出库日期
                String exitDateString = record.getExitDate().split("T")[0];
                exitDateObj = LocalDate.parse(exitDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid exit date format.", e);
            }

            double inTon = record.getInTon();
            LocalDate feeStartDate = entryDateObj.plusDays(freeDays);

            // 累计计算存储费前，先检查是否有足够的吨数进行出库
            if (inTon > 0 && !exitDateObj.isBefore(feeStartDate)) {
                // 计算从应收费用开始日期到退出日期（或当前日期，如果仍在存储中）的费用
                long chargeableDays = ChronoUnit.DAYS.between(feeStartDate, exitDateObj);

                if (inTon >= outTon){
                    totalFee += outTon * chargeableDays * storageFeePerTonPerDay;
                    record.setInTon(inTon - outTon);
                } else {
                    totalFee += (outTon - inTon) * chargeableDays * storageFeePerTonPerDay;
                    record.setProcessedForOutstore(true);
                    record.setOutTon(outTon - inTon);
                }
            }
        }

        return totalFee;
    }
}
