import request from '@/utils/request'

export default{
  getInstoreList(searchModel){
    return request({
      url: '/instore/list', 
      method: 'get',
      params:{
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        inId: searchModel.inId,
        inStoreid: searchModel.inStoreid
      }
    });
  },
  addInstore(instore){
    return request({
      url: '/instore', 
      method: 'post',
      data: instore
    });
  },
  updateInstore(instore){
    return request({
      url: '/instore', 
      method: 'put',
      data: instore
    });
  },
  saveInstore(instore){
    if (instore.inId == null || instore.inId === undefined){
      return this.addInstore(instore);
    }
    return this.updateInstore(instore);
  },
  getInstoreById(inId){
    return request({
      url: '/instore/' + inId, 
      method: 'get'
    });
  },
  deleteInstore(inId){
    return request({
      url: '/instore/' + inId, 
      method: 'delete'
    });
  }
}