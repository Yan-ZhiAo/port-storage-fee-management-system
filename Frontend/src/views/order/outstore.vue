<template>
    <div>
      <el-card id="search">
        <el-row>
          <el-col :span="20">
            <el-input v-model="searchModel.outId" placeholder="出库单号" clearable></el-input>
            <el-input v-model="searchModel.outStoreid" placeholder="关联仓库ID" clearable></el-input>
            <el-button @click="getOutstoreList" type="primary" round icon="el-icon-search">查询</el-button>
          </el-col>
          <el-col :span="4" align="right">
            <el-button @click="openEditUI(null)" type="primary" icon="el-icon-plus" circle></el-button>
          </el-col>
        </el-row>
      </el-card>

      <el-card>
        <el-table :data="outstoreList" stripe style="width: 100%">
          <el-table-column type="index" label="#" width="80">
            <template slot-scope="scope">
              {{ (searchModel.pageNo - 1) * searchModel.pageSize + scope.$index + 1 }}
            </template>
          </el-table-column>
          <el-table-column prop="outId" label="出库单号" width="180">
          </el-table-column>
          <el-table-column prop="outDate" label="出库时间" width="180">
            <template slot-scope="scope">
            <span>
                {{ 
                new Date(scope.row.outDate).getFullYear() + '-' +
                ('0' + (new Date(scope.row.outDate).getMonth() + 1)).slice(-2) + '-' +
                ('0' + new Date(scope.row.outDate).getDate()).slice(-2)
                }}
            </span>
            </template>
          </el-table-column>
          <el-table-column prop="outTon" label="出库吨数" width="150">
          </el-table-column>
          <el-table-column prop="outCasenum" label="出库件数" width="150">
          </el-table-column>
          <el-table-column prop="outStoreid" label="所在仓库">
          </el-table-column>
          <el-table-column prop="outCost" label="堆存费" width="180">
          </el-table-column>
          <el-table-column label="操作" width="150">
            <template slot-scope="scope"> 
              <el-button @click="openEditUI(scope.row.outId)" type="primary" icon="el-icon-edit" size="mini" circle></el-button>
              <el-button @click="deleteOutstore(scope.row)" type="danger" icon="el-icon-delete" size="mini" circle></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>

      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page="searchModel.pageNo" :page-sizes="[10, 20, 30, 50]" :page-size="searchModel.pageSize"
        layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>

      <el-dialog @close="clearForm" :title="title" :visible.sync="dialogFormVisible">
        <el-form :model="outstoreForm" ref="outstoreFormRef" :rules="rules">
        <el-form-item label="出库时间" prop="outDate" :label-width="formLabelWidth">
            <el-col :span="11">
            <el-date-picker type="date" placeholder="选择日期" v-model="outstoreForm.outDate" style="width: 100%;"></el-date-picker>
            </el-col>
        </el-form-item>
        <el-form-item label="出库吨数" prop="outTon" :label-width="formLabelWidth">
            <el-input v-model="outstoreForm.outTon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="出库件数" prop="outCasenum" :label-width="formLabelWidth">
            <el-input v-model="outstoreForm.outCasenum" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="所在仓库" prop="outStoreid" :label-width="formLabelWidth">
            <el-input v-model="outstoreForm.outStoreid" autocomplete="off"></el-input>
        </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveOutstore">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </template>

<script>
import outstoreApi from '@/api/outstoreManage'; 
import { title } from '@/settings';

export default {
  data() {
    return {
      formLabelWidth: '100px',
      outstoreForm: {}, 
      dialogFormVisible: false,
      title: "",
      total: 0,
      searchModel: {
        pageNo: 1,
        pageSize: 10
      },
      outstoreList: [], 
      rules: {
        outTon: [ 
          { required: true, message: '请输入出库吨数', trigger: 'blur' }
        ],
        outCasenum: [ 
          { required: true, message: '请输入出库件数', trigger: 'blur' }
        ],
        outDate: [ 
          { required: true, message: '请输入出库时间', trigger: 'blur' }
        ],
        outStoreid: [ 
          { required: true, message: '请输入所在仓库', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    deleteOutstore(outstore){ 
      this.$confirm(`您确认删除出库记录 ${outstore.outId} 吗？`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        outstoreApi.deleteOutstore(outstore.outId).then(response => {
          this.$message({
            message: response.message,
            type: 'success'
          });
          this.getOutstoreList();
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    saveOutstore(){ 
      this.$refs.outstoreFormRef.validate(valid => { 
        if (valid) {
          outstoreApi.saveOutstore(this.outstoreForm).then(response => {
            this.$message({
              message: response.message,
              type: 'success'
            });
            this.dialogFormVisible = false;
            this.getOutstoreList();
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    clearForm(){
      this.outstoreForm = {}; 
      this.$refs.outstoreFormRef.clearValidate(); 
    },
    openEditUI(outstoreId){ 
      if(outstoreId == null){
        this.title = "新增出库";
      }else{
        this.title = "修改出库";
        outstoreApi.getOutstoreById(outstoreId).then(response => {
          this.outstoreForm = response.data;
        });
      }
      this.dialogFormVisible = true;
    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize;
      this.getOutstoreList();
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo;
      this.getOutstoreList();
    },
    getOutstoreList() { 
      outstoreApi.getOutstoreList(this.searchModel).then(response => {
        this.outstoreList = response.data.rows;
        this.total = response.data.total;
      });
    }
  },
  created() {
    this.getOutstoreList(); 
  }
};
</script>

<style>
#search .el-input {
  width: 200px;
  margin-right: 20px;
}
.el-dialog .el-input{
  width: 90%;
}
</style>