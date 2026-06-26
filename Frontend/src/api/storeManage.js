import request from '@/utils/request'

export default{
  getStoreList(searchModel){
    return request({
      url: '/store/list', 
      method: 'get',
      params:{
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        storeId: searchModel.storeId,
        storeBelongcompany: searchModel.storeBelongcompany
      }
    });
  },
  addStore(store){
    return request({
      url: '/store', 
      method: 'post',
      data: store
    });
  },
  updateStore(store){
    return request({
      url: '/store', 
      method: 'put',
      data: store
    });
  },
  saveStore(store){
    if (store.storeId == null || store.storeId == undefined){
      return this.addStore(store);
    }
    return this.updateStore(store);
  },
  getStoreById(storeId){
    return request({
      url: '/store/' + storeId, 
      method: 'get'
    });
  },
  deleteStore(storeId){
    return request({
      url: '/store/' + storeId, 
      method: 'delete'
    });
  }
}