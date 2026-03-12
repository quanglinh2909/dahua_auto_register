module.exports = {
  apps: [
    {
      name: 'auto-register',
      script: './run.sh com.netsdk.demo.AutoRegisterDemo',
      cwd: './',  // hoặc bỏ qua nếu file ecosystem nằm trong cùng thư mục
      autorestart: true,
      watch: false,
    },
  ],
};
