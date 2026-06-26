import request from '@/utils/request'

export default{
  getCompanyList(seachModel){
    return request({
      url: '/company/list', 
      method: 'get',
      params:{
        pageNo: seachModel.pageNo,
        pageSize: seachModel.pageSize,
        companyName: seachModel.companyName,
        companyBelong: seachModel.companyBelong
      }
    });
  },
  addCompany(company){
    return request({
      url: '/company', 
      method: 'post',
      data: company
    });
  },
  updateCompany(company){
    return request({
      url: '/company', 
      method: 'put',
      data: company
    });
  },
  saveCompany(company){
    if (company.companyId == null || company.companyId == undefined){
      return this.addCompany(company);
    }
    return this.updateCompany(company);
  },
  getCompanyById(companyId){
    return request({
      url: '/company/' + companyId, 
      method: 'get'
    });
  },
  deleteCompany(companyId){
    return request({
      url: '/company/' + companyId, 
      method: 'delete'
    });
  }
}
