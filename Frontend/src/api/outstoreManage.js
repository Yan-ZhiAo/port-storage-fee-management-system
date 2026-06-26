import request from '@/utils/request';

export default {
  getOutstoreList(searchModel) {
    return request({
      url: '/outstore/list',
      method: 'get',
      params: {
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        outId: searchModel.outId,
        outStoreid: searchModel.outStoreid
      }
    });
  },
  addOutstore(outstore) {
    return request({
      url: '/outstore',
      method: 'post',
      data: outstore
    });
  },
  updateOutstore(outstore) {
    return request({
      url: '/outstore',
      method: 'put',
      data: outstore
    });
  },
  saveOutstore(outstore) {
    if (outstore.outId == null || outstore.outId === undefined) {
      return this.addOutstore(outstore);
    }
    return this.updateOutstore(outstore);
  },
  getOutstoreById(outId) {
    return request({
      url: '/outstore/' + outId,
      method: 'get'
    });
  },
  deleteOutstore(outId) {
    return request({
      url: '/outstore/' + outId,
      method: 'delete'
    });
  }
}