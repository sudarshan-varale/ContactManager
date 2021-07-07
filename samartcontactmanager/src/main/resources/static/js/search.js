
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
