// 자바 스크립트 공통 부분
Number.prototype.formatBytes = function (decimals) {
   const bytes = this; // this는 Number 객체
   if(bytes == 0) return '0 Bytes';

   let k = 1024,
       dm = decimals || 3,
       sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
       i = Math.floor(Math.log(bytes) / Math.log(k));

   return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
}
