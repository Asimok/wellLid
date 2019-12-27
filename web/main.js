


window.onload=function(){
    //给按钮添加单击事件
    document.getElementById("send").onclick=function(){


        getXHR();
    }




}
/**
 * 得到XMLHttpRequest对象
 */
function getXHR(){
    var xmlHttp;
    try {
        xmlHttp=new XMLHttpRequest();
    }catch(e)
    {
        try{
            xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch(e)
        {
            try{
                xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
            }
            catch(e)
            {
                alert("你的浏览器不支持ajax");
                return false;
            }

        }

    }
    return xmlHttp;
}
function down(){
    String
    var data;
    data = "username=mq"+"&sessionId=CNNO9527"  + "&code=1" ;

    var url = "http://192.168.31.114:8080/wellLid/servlet/AppControlServlet?username=mq&sessionId=CONN_9527&code=bbbbb"
    var request = new XMLHttpRequest();
    request.open("POST", url);
    request.send(data);
    alert("hello");


}

