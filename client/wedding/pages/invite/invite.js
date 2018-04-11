// pages/invite/invite.js
var net = require('../../utils/net.js')
var reAuth = require('../../utils/reAuth.js')
var app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    invited:false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    if (!(app.globalData.userInfo)){
      reAuth.reAuth((res)=>{
        if("OK" == res){
          that.judgeInvite();
        }
      });
    }else{
      that.judgeInvite();
    }
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
    this.judgeInvite();
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

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },

  judgeInvite:function(){
    var that = this;
    if (app.globalData.userInfo && (that.data.judged != true)){
      net.post("/user/invite", app.globalData.userInfo, (data) => {
        if (data.inviteName == "") {//未邀请
          that.setData({ invited: false,judged:true });
          app.globalData.invitedName = "暂未被邀请";
        } else {//已邀请
          that.setData({ invited: true, judged: true,address:data.address});
          app.globalData.invitedName = data.inviteName;

        }
        this.setData({
          invitedName: app.globalData.invitedName
        })
      })
    }
  }
})