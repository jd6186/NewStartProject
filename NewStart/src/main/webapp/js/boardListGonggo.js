function CreditUseing(){
   var result = confirm("공고를 작성하시겠습니까?");
   $.ajax({
      url : "./userCredit.do",
      type : "post",
      data : {"credit" : "10"},
      dataType : "text",
      success : function(data){
         if(data == "true"){
        	 alert("공고 작성이 완료되었습니다.");
         } else {
        	 alert("결제 해 병신아!!ㅗㅗㅗㅗㅗㅗㅗㅗ");
        	 location.href="./pay.do";
         }
      },
      error : function(){
         alert("공고작성이 취소되었습니다.");
         
      }   
   });
}



//----------------------------------------------- 삭제 -----------------------------------------------
function del(val){
   alert("삭제할 seq : " + val);
   location.href="./delGonggo.do?seq="+val;
}


function checkAll(bool){
   var chkVals = document.getElementsByName("chkVal");
   for (var i = 0; i < chkVals.length; i++) {
      chkVals[i].checked = bool;
   }
}

function chkbox(){
   var chkVals = document.getElementsByName("chkVal");
   var n = 0;
   for (var i = 0; i < chkVals.length; i++) {
      if(chkVals[i].checked){
         n++;
      }
   }
   if(n>0){
      document.getElementById("frm").action="./multiDel.do";
      
   } else {
      swal("게시글 오류", "삭제할 값이 없습니다.");
      return false;
   }
   
}


//---------------------------------------------- 글 수정 --------------------------------------------------------


// 이거하나로 수정페이지로 이동시키기
function modify(val){
   location.href = "./modifyFormGonggo.do?gonggo_seq="+val;
}




function update(){
   var frm = document.getElementById("frmModify");
   // 해당 글의 seq를 뽑아 보내자 몇개가 됐든간에 처리가능하게
   var content = $("#content").val();
   var gonggo_cost = $("#gonggo_cost").val();
   var Gonggo_seq = $("#Gonggo_seq").val();
   frm.action="./modifyGonggo.do?content="+content+"&gonggo_cost="+gonggo_cost+"&Gonggo_seq="+Gonggo_seq;
   if(gonggo_cost == ""){ // 자바랑 null 확인하는 방법이 다르다.
      swal("글 수정 오류", "공고 금액 입력은 필수입니다.");
   }else if(content == ""){
      swal("글 수정 오류", "내용 입력은 필수입니다.");
   }else {
      alert(frm.method);
      frm.submit();
   }
}



// -------------------------------------- 글 작성 관리 --------------------------------------------------------
function GonggoCreate(){
   if($("#userType").val() == "M"){
      location.href = "./boardInsertM.do";
   } else {
      location.href = "./boardInsertT.do";
   }
}









// ------------------------------------- 페이징 관리 -------------------------------------------------------------------

//첫번째 데이터는 lists로 하고 페이지 처리된 것들을 5개 칸에 5개의 글이 있을 때 10개의 글이 있으면 3페이지로 바꾸게 만들어주는 역할을 할 예정
function pageAjax(){
//   alert("아작아작");
   $.ajax({
      url:"./page.do",
      type:"post",
      async: true,
      data: $("#frm").serialize(), // 키는 벨류 형태로 자동으로 만들어준다.
      dataType: "json",
      success: function(msg){
//         alert(msg.lists[1].title);
//         alert(msg.row.total);
         $.each(msg, function(key,value){  // lists, {"",[]}  // row,{}
            var varHtml = "";
            var n = $(".table tr:eq(0) th").length; // 테이블에 첫번째 tr에 th의 개수
            if(key == "lists"){
               varHtml += "<tr>";
               varHtml += "   <th><input type='checkbox' onclick='checkAll(this.checked)'></th>";
               varHtml += "   <th>글번호</th>";
               varHtml += "   <th>제목</th>";
               varHtml += "   <th>작성자</th>";
               varHtml += "   <th>조회수</th>";
               if(n == 7){
                  varHtml += "   <th>삭제 여부</th>";
               }
               varHtml += "   <th>작성일</th>";
               varHtml += "</tr>";
               
               // 위에 형태들을 보면
               //[{dto},{dto},{dto}]
               // 그래서 또 for문 필요
               $.each(value,function(k,v){
                  varHtml += "<tr>";
                  varHtml += "   <td><input type='checkbox' name='chkVal' value='"+v.seq+"'></td>";
                  varHtml += "   <td>"+v.seq+"</td>";
                  varHtml += "   <td><div class='panel-heading'><a data-toggle='collapse' data-parent='#accordion' href='#collapse"+v.seq+"' onclick='collapse(\""+v.seq+"\")'>"+v.title+"</a></div></td>";
                  varHtml += "             <td>"+v.id+"</td>";
                  varHtml += "   <td>"+v.readcount+"</td>";
                  if(n == 7){
                  varHtml += "   <td>"+v.delflag+"</td>";
                  }
                  varHtml += "   <td>"+v.regdate+"</td>";
                  varHtml += "</tr>"; 
               
               
               
               
               // 글 내용 입력하기
               varHtml += "<tr>";
               varHtml += "   <td colspan='"+n+"'>";                                                                                     
               varHtml += "      <div id='collapse"+v.seq+"' class='panel-collapse'>";
               varHtml += "         <div class='form-group'>";
               varHtml += "         <label>내용</label>";
               varHtml += "         <textarea rows='7' class='form-control' readonly='readonly'>"+v.content+"</textarea>";
               varHtml += "         </div>";
               varHtml += "         <div>";
               varHtml += "            <div class ='form-group'>";
               if(v.id == v.meid){
               varHtml += "               <input class='btn btn-primary btn-center' type='button' value='글 수정' onclick='modify(\""+v.seq+"\")'>";
               }
               if(v.id == v.meid || n == 7){
               varHtml += "               <input class='btn btn-primary btn-center' type='button' value='글 삭제' onclick='del(\""+v.seq+"\")'>";
               }
               if(v.id == v.meid || n != 7){
               varHtml += "               <input class='btn btn-primary btn-center' type='button' value='답글' onclick='reply(\""+v.seq+"\")'>";
               }
               varHtml += "            </div>";
               varHtml += "         </div>";
               varHtml += "      </div>";             
               varHtml += "   </td>";
               varHtml += "</tr>";
               });
            } else {
               varHtml += "<li><a  href='#' onclick='pageFirst()'>&laquo;</a></li>";
               varHtml += "<li><a  href='#' onclick='pagePre("+value.pageNum+", "+value.pageList+")'>&lsaquo;</a></li>";
               for(var j = value.pageNum; j <= value.count; j++){
                  varHtml += "   <li><a href='#' onclick='pageIndex("+j+")'>"+j+"</a></li>";
               }
               varHtml += "<li><a  href='#' onclick='pageNext("+value.pageNum+", "+value.pageList+", "+value.total+", "+value.listNum+")'>&rsaquo;</a></li>";
               varHtml += "<li><a  href='#' onclick='pageLast("+value.pageNum+", "+value.pageList+", "+value.total+", "+value.listNum+")'>&raquo;</a></li>";
         
            }
            
            
            if(key == "lists"){
               $("table > tbody").html(varHtml);
            } else{
               $(".pagination").html(varHtml);
            }
            
            
         });
      },
      error: function(){
         alert("데이터 처리 실패");
      } 
   });
}


// ---------------------------------------------------------------- 공고금액 입력 시 유효값 처리 -------------------------------------------------------------------------
var gongo_cost =  document.getElementById("gongo_cost");


$(function(){
   $("input:text[numberOnly]").on("keyup", function() {
      $(this).val(addCommas($(this).val().replace(/[^0-9]/g,"")));
   });
});


//3자리 단위마다 콤마 생성

function addCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}
 
//모든 콤마 제거
function removeCommas(x) {
    if(!x || x.length == 0) return "";
    else return x.split(",").join("");
}

