<template>
  <div>
    <el-card id="search">
      <el-row>
        <el-col :span="20">
          <el-input v-model="searchModel.companyName" placeholder="公司名称" clearable></el-input>
          <el-input v-model="searchModel.companyBelong" placeholder="所属港口" clearable></el-input>
          <el-button @click="getCompanyList" type="primary" round icon="el-icon-search">查询</el-button>
        </el-col>
        <el-col :span="4" align="right">
          <el-button @click="openEditUI(null)" type="primary" icon="el-icon-plus" circle></el-button>
        </el-col>
      </el-row>
    </el-card>

    <el-card>
      <el-table :data="companyList" stripe style="width: 100%">
        <el-table-column typed="index" label="#" width="80">
          <template slot-scope="scope">
            {{ (searchModel.pageNo - 1) * searchModel.pageSize + scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column prop="companyId" label="公司ID" width="150">
        </el-table-column>
        <el-table-column prop="companyName" label="公司名称" width="150">
        </el-table-column>
        <el-table-column prop="companyBelong" label="所属港口" width="180">
        </el-table-column>
        <el-table-column prop="companyPrincipal" label="负责人" width="180">
        </el-table-column>
        <el-table-column prop="companyPhone" label="电话" width="180">
        </el-table-column>
        <el-table-column prop="companyCost" label="堆存费总计">
        </el-table-column>
        <el-table-column label="操作" width="150">
          <template slot-scope="scope"> 
            <el-button @click="openEditUI(scope.row.companyId)" type="primary" icon="el-icon-edit" size="mini" circle></el-button>
            <el-button @click="deleteCompany(scope.row)" type="danger" icon="el-icon-delete" size="mini" circle></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
      :current-page="searchModel.pageNo" :page-sizes="[10, 20, 30, 50]" :page-size="searchModel.pageSize"
      layout="total, sizes, prev, pager, next, jumper" :total="total">
    </el-pagination>

    <el-dialog @close="clearForm" :title="title" :visible.sync="dialogFormVisible">
      <el-form :model="companyForm" ref="companyFormRef" :rules="rules">
        <el-form-item label="公司名称" prop="companyName" :label-width="formLabelWidth">
          <el-input v-model="companyForm.companyName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="所属港口" prop="companyBelong" :label-width="formLabelWidth">
          <el-input v-model="companyForm.companyBelong" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="负责人" prop="companyPrincipal" :label-width="formLabelWidth">
          <el-input v-model="companyForm.companyPrincipal" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="companyPhone" :label-width="formLabelWidth">
          <el-input v-model="companyForm.companyPhone" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveCompany">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import companyApi from '@/api/companyManage'
import { title } from '@/settings';
export default {
  data() {
    var checkPhone = (rule, value, callback) => {
        var reg = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
        if (!reg.test(value)) {
          return callback(new Error('手机格式错误'));
        }
        callback();
      };
    return {
      formLabelWidth: '100px',
      companyForm: {},
      dialogFormVisible: false,
      title: "",
      total: 0,
      searchModel: {
        pageNo: 1,
        pageSize: 10
      },
      companyList: [],
      rules:{
        companyName: [
            { required: true, message: '请输入公司名称', trigger: 'blur' }
          ],
        companyBelong: [
            { required: true, message: '请输入所属港口', trigger: 'blur' }
          ],
          companyPrincipal: [
            { required: true, message: '请输入负责人名称', trigger: 'blur' }
          ],  
        companyPhone: [
            { required: true, message: '请输入联系电话', trigger: 'blur' },
            { validator: checkPhone, trigger: 'blur' }
          ]
      }
    }
  },
  methods: {
    deleteCompany(company){
      this.$confirm(`您确认删除公司 ${company.companyName} 吗？`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          companyApi.deleteCompany(company.companyId).then(response => {
            this.$message({
              message: response.message,
              type: 'success'
            });
            this.getCompanyList();
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
    },
    saveCompany(){
      this.$refs.companyFormRef.validate((valid) => {
          if (valid) {
            companyApi.saveCompany(this.companyForm).then(response => {
              this.$message({
              message: response.message,
              type: 'success'
            });
              this.dialogFormVisible = false;
              this.getCompanyList();
            });
          } else {
            console.log('error submit!!');
            return false;
          }
        });
    },
    clearForm(){
      this.companyForm = {};
      this.$refs.companyFormRef.clearValidate();
    },
    openEditUI(companyId){
      if(companyId == null){
        this.title = "新增公司";
      }else{
        this.title = "修改公司";
        companyApi.getCompanyById(companyId).then(response => {
          this.companyForm = response.data;
        });
      }
      this.dialogFormVisible = true;
    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize;
      this.getCompanyList();
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo;
      this.getCompanyList();
    },
    getCompanyList() {
      companyApi.getCompanyList(this.searchModel).then(response => {
        this.companyList = response.data.rows;
        this.total = response.data.total;
      });
    }
  },
  created() {
    this.getCompanyList();
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