// pages/pic_group_detail/pic_group_detail.js
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
    var winWidth = null, dp = 1;
    try {
      var winWidth = wx.getSystemInfoSync().windowWidth;
    } catch (e) { };
    if (winWidth) {
      dp = winWidth / 750;
    }
    this.dp = dp;
    this.setData({pic:options.pic});
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
  
  },
  imageLoad: function (e) {
    var id = e.currentTarget.dataset.src;
    var img_w = e.detail.width;
    var img_h = e.detail.height;
    var ratio = 750 / img_w;

    if ((img_w / this.dp) >= 750) {
      var imageStyle = 'width: ' + 750 + 'rpx; height:' + img_h * ratio + 'rpx;';
    } else {
      var imageStyle = 'width: ' + img_w + 'px; height:' + img_h + 'px;';
    }
    this.setData({ picstyle: imageStyle });

  }
})