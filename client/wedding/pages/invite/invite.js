// pages/invite/invite.js
var net = require('../../utils/net.js')
var app = getApp()
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
    net.post("/user/invite",app.globalData.userInfo,(data)=>{
      if(data == ""){//未邀请
        app.globalData.invitedName = "暂未被邀请";
        wx.navigateTo({
              url: '/pages/notinvite/notinvite',
            });
      }else{//已邀请
        app.globalData.invitedName = data;
        wx.navigateTo({
              url: '/pages/invite/invite',
            });
      }
      this.setData({
        invitedName:app.globalData.invitedName
      })
    })
    if (app.globalData.invitedName){
      this.setData({
        invitedName: app.globalData.invitedName
      })
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
  
  }
})