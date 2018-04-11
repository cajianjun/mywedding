// pages/pic_group/pic_group.js
var net = require('../../utils/net.js')
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
  
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var groupId  = options.groupId;
    net.post("/pic/pics",groupId,picList=>{
      that.setData({
        pics:picList
      })
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },
  imgTap:function(e){
    console.log(e);
    if (e.currentTarget.dataset.id != 0) {
      console.log(e.currentTarget.dataset.id);
      var groupPath = e.currentTarget.dataset.id;
      var detailPath = groupPath.substring(0, groupPath.lastIndexOf("."))
        + "_d." + groupPath.substring(groupPath.lastIndexOf(".") + 1, 
        groupPath.length);
      wx.navigateTo({
        url: "/pages/pic_group_detail/pic_group_detail?pic=" + detailPath
      })
    }
  },
  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})