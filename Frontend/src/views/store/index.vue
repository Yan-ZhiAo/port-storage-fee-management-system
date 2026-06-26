<template>
  <div>
    <el-card id="search">
      <el-row>
        <el-col :span="20">
          <el-input v-model="searchModel.storeId" placeholder="仓库ID" clearable></el-input>
          <el-input v-model="searchModel.storeBelongcompany" placeholder="所属公司" clearable></el-input>
          <el-button @click="getStoreList" type="primary" round icon="el-icon-search">查询</el-button>
        </el-col>
        <el-col :span="4" align="right">
          <el-button @click="openEditUI(null)" type="primary" icon="el-icon-plus" circle></el-button>
        </el-col>
      </el-row>
    </el-card>

    <el-card>
      <el-table :data="storeList" stripe style="width: 100%">
        <el-table-column type="index" label="#" width="80">
          <template slot-scope="scope">
            {{ (searchModel.pageNo - 1) * searchModel.pageSize + scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column prop="storeId" label="仓库ID" width="200">
        </el-table-column>
        <el-table-column prop="storeTon" label="所剩吨数" width="180">
        </el-table-column>
        <el-table-column prop="storeCasenum" label="所剩件数" width="180">
        </el-table-column>
        <el-table-column prop="storeBelongport" label="所属港口" width="180">
        </el-table-column>
        <el-table-column prop="storeBelongcompany" label="所属公司">
        </el-table-column>
        <el-table-column label="操作" width="150">
          <template slot-scope="scope"> 
            <el-button @click="openEditUI(scope.row.storeId)" type="primary" icon="el-icon-edit" size="mini" circle></el-button>
            <el-button @click="deleteStore(scope.row)" type="danger" icon="el-icon-delete" size="mini" circle></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
      :current-page="searchModel.pageNo" :page-sizes="[10, 20, 30, 50]" :page-size="searchModel.pageSize"
      layout="total, sizes, prev, pager, next, jumper" :total="total">
    </el-pagination>

    <el-dialog @close="clearForm" :title="title" :visible.sync="dialogFormVisible">
      <el-form :model="storeForm" ref="storeFormRef" :rules="rules">
        <el-form-item label="所剩吨数" prop="storeTon" :label-width="formLabelWidth">
          <el-input v-model="storeForm.storeTon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="所剩件数" prop="storeCasenum" :label-width="formLabelWidth">
          <el-input v-model="storeForm.storeCasenum" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="所属港口" prop="storeBelongport" :label-width="formLabelWidth">
          <el-input v-model="storeForm.storeBelongport" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="所属公司" prop="storeBelongcompany" :label-width="formLabelWidth">
          <el-input v-model="storeForm.storeBelongcompany" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveStore">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import storeApi from '@/api/storeManage' 
import { title } from '@/settings';
export default {
  data() {
    return {
      formLabelWidth: '100px',
      storeForm: {}, 
      dialogFormVisible: false,
      title: "",
      total: 0,
      searchModel: {
        pageNo: 1,
        pageSize: 10
      },
      storeList: [], 
      rules: {
        storeTon: [ 
          { required: true, message: '请输入所剩吨数', trigger: 'blur' }
        ],
        storeCasenum: [ 
          { required: true, message: '请输入所剩件数', trigger: 'blur' }
        ],
        storeBelongport: [ 
          { required: true, message: '请输入所属港口', trigger: 'blur' }
        ],
        storeBelongcompany: [ 
          { required: true, message: '请输入所属公司', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    deleteStore(store){ 
      this.$confirm(`您确认删除仓库 ${store.storeId} 吗？`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        storeApi.deleteStore(store.storeId).then(response => {
          this.$message({
            message: response.message,
            type: 'success'
          });
          this.getStoreList();
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    saveStore(){ 
      this.$refs.storeFormRef.validate(valid => { 
        if (valid) {
          storeApi.saveStore(this.storeForm).then(response => {
            this.$message({
              message: response.message,
              type: 'success'
            });
            this.dialogFormVisible = false;
            this.getStoreList();
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    clearForm(){
      this.storeForm = {}; 
      this.$refs.storeFormRef.clearValidate(); 
    },
    openEditUI(storeId){ 
      if(storeId == null){
        this.title = "新增仓库";
      }else{
        this.title = "修改仓库";
        storeApi.getStoreById(storeId).then(response => {
          this.storeForm = response.data;
        });
      }
      this.dialogFormVisible = true;
    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize;
      this.getStoreList();
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo;
      this.getStoreList();
    },
    getStoreList() { 
      storeApi.getStoreList(this.searchModel).then(response => {
        this.storeList = response.data.rows;
        this.total = response.data.total;
      });
    }
  },
  created() {
    this.getStoreList(); 
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