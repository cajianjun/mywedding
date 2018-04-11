var rootPath = "https://wedding.nobbican.com";
function post(path,data,cb){
  if (!getApp().globalData.token){
    return;
  }
	wx.request({
          url: rootPath + path,
          method:'POST',
          header: {
            'content-type': 'application/json'
          },
          data: {
          	token:getApp().globalData.token,
            data:data
          },
          success: (resp) => {
            cb(resp.data.data);
          }
        })
}

function getR(path,cb){
  wx.request({
          url: rootPath + path,
          method:'GET',
          header: {
            'content-type': 'application/json'
          },
          success: (resp) => {
            cb(resp.data.data);
          }
        })
}

module.exports = {
  post: post,
  get:getR
}