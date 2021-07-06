const function=()=>
 {
 let password=$("#password_filed").val();
  let password1=$("#password_filed1").val();
 
 
  if (password==
         password1)
          {
    document.getElementById('message').style.color = 'green';
    document.getElementById('message').innerHTML = 'matching';
  } else {
    document.getElementById('message').style.color = 'red';
    document.getElementById('message').innerHTML = 'not matching';
  }
}
const search=()=>
{
let query=$("#search_field").val();

if(query=="")
{
$(".result").hide();
}
else
{
console.log(query);
$(".result").show();
}
};
