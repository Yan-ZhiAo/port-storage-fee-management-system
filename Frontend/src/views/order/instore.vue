<template>
    <div>
      <el-card id="search">
        <el-row>
          <el-col :span="20">
            <el-input v-model="searchModel.inId" placeholder="入库单号" clearable></el-input>
            <el-input v-model="searchModel.inStoreid" placeholder="关联仓库ID" clearable></el-input>
            <el-button @click="getInstoreList" type="primary" round icon="el-icon-search">查询</el-button>
          </el-col>
          <el-col :span="4" align="right">
            <el-button @click="openEditUI(null)" type="primary" icon="el-icon-plus" circle></el-button>
          </el-col>
        </el-row>
      </el-card>
  
      <el-card>
        <el-table :data="instoreList" stripe style="width: 100%">
          <el-table-column type="index" label="#" width="80">
            <template slot-scope="scope">
              {{ (searchModel.pageNo - 1) * searchModel.pageSize + scope.$index + 1 }}
            </template>
          </el-table-column>
          <el-table-column prop="inId" label="入库单号" width="180">
          </el-table-column>
          <el-table-column prop="inTime" label="入库时间" width="180">
            <template slot-scope="scope">
            <span>
                {{ 
                new Date(scope.row.inTime).getFullYear() + '-' +
                ('0' + (new Date(scope.row.inTime).getMonth() + 1)).slice(-2) + '-' +
                ('0' + new Date(scope.row.inTime).getDate()).slice(-2)
                }}
            </span>
            </template>
          </el-table-column>
          <el-table-column prop="inCarid" label="车牌号" width="180">
          </el-table-column>
          <el-table-column prop="inTon" label="入库吨数" width="150">
          </el-table-column>
          <el-table-column prop="inCasenum" label="入库件数" width="150">
          </el-table-column>
          <el-table-column prop="inStoreid" label="所在仓库">
          </el-table-column>
          <el-table-column label="操作" width="150">
            <template slot-scope="scope"> 
              <el-button @click="openEditUI(scope.row.inId)" type="primary" icon="el-icon-edit" size="mini" circle></el-button>
              <el-button @click="deleteInstore(scope.row)" type="danger" icon="el-icon-delete" size="mini" circle></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
  
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page="searchModel.pageNo" :page-sizes="[10, 20, 30, 50]" :page-size="searchModel.pageSize"
        layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>

      <el-dialog @close="clearForm" :title="title" :visible.sync="dialogFormVisible">
        <el-form :model="instoreForm" ref="instoreFormRef" :rules="rules">
        <el-form-item label="入库时间" prop="inTime" :label-width="formLabelWidth">
            <el-col :span="11">
            <el-date-picker type="date" placeholder="选择日期" v-model="instoreForm.inTime" style="width: 100%;"></el-date-picker>
            </el-col>
        </el-form-item>
        <el-form-item label="车牌号" prop="inCarid" :label-width="formLabelWidth">
            <el-input v-model="instoreForm.inCarid" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="入库吨数" prop="inTon" :label-width="formLabelWidth">
            <el-input v-model="instoreForm.inTon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="入库件数" prop="inCasenum" :label-width="formLabelWidth">
            <el-input v-model="instoreForm.inCasenum" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="所在仓库" prop="inStoreid" :label-width="formLabelWidth">
            <el-input v-model="instoreForm.inStoreid" autocomplete="off"></el-input>
        </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveInstore">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </template>

<script>
import instoreApi from '@/api/instoreManage'; 
import { title } from '@/settings';

export default {
  data() {
    return {
      formLabelWidth: '100px',
      instoreForm: {}, 
      dialogFormVisible: false,
      title: "",
      total: 0,
      searchModel: {
        pageNo: 1,
        pageSize: 10
      },
      instoreList: [], 
      rules: {
        inTon: [ 
          { required: true, message: '请输入入库吨数', trigger: 'blur' }
        ],
        inCasenum: [ 
          { required: true, message: '请输入入库件数', trigger: 'blur' }
        ],
        inCarid: [ 
          { required: true, message: '请输入车牌号', trigger: 'blur' }
        ],
        inTime: [ 
          { required: true, message: '请输入入库时间', trigger: 'blur' }
        ],
        inStoreid: [ 
          { required: true, message: '请输入所在仓库', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    deleteInstore(instore){ 
      this.$confirm(`您确认删除库存 ${instore.inId} 吗？`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        instoreApi.deleteInstore(instore.inId).then(response => {
          this.$message({
            message: response.message,
            type: 'success'
          });
          this.getInstoreList();
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    saveInstore(){ 
      this.$refs.instoreFormRef.validate(valid => { 
        if (valid) {
          instoreApi.saveInstore(this.instoreForm).then(response => {
            this.$message({
              message: response.message,
              type: 'success'
            });
            this.dialogFormVisible = false;
            this.getInstoreList();
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    clearForm(){
      this.instoreForm = {}; 
      this.$refs.instoreFormRef.clearValidate(); 
    },
    openEditUI(instoreId){ 
      if(instoreId == null){
        this.title = "新增入库";
      }else{
        this.title = "修改入库";
        instoreApi.getInstoreById(instoreId).then(response => {
          this.instoreForm = response.data;
        });
      }
      this.dialogFormVisible = true;
    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize;
      this.getInstoreList();
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo;
      this.getInstoreList();
    },
    getInstoreList() { 
      instoreApi.getInstoreList(this.searchModel).then(response => {
        this.instoreList = response.data.rows;
        this.total = response.data.total;
      });
    }
  },
  created() {
    this.getInstoreList(); 
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