define(function(require, exports) {
    // 通过 exports 对外提供接口
    exports.path ="http://localhost:8084/platform/";

    exports.remove=function (arr, item) {
        var newArr = arr.slice(0);
        for(var i=0; i<newArr.length; i++) {
            if(newArr[i] == item) {
                newArr.splice(i, 1);
            }
        }
        return newArr;
    }
    exports.splitNumArray=function (str,splitStr) {
        var strArray=str.split(splitStr);
        var numArray=new Array();
        for (var i=0;i<strArray.length;i++)
        {
            if ( $.trim(strArray[i])!=null&&$.trim(strArray[i])!="")
            {
                numArray.push(Number(strArray[i]));
            }
        }
        return numArray;
    }
});