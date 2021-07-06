


//for tooggle
const toggleSlidebar=()=>
{
if($(".slidebar").is(":visible"))
{
$(".slidebar").css("display","none");
$(".content").css("margin-left","0%");	
}
else
{
$(".slidebar").css("display","block");
$(".content").css("margin-left","20%");
 }
};










//for payment

const paymentStart=()=>
{
console.log("payment started")
let amount=$("#payment_filed").val()	
console.log(amount);
if(amount==''||amount==null)
{
alert("Amount is required");
return;
}
 $.ajax({
        type: "POST",
        contentType: 'application/json',
        url: "/user/createorder",
        data: JSON.stringify({amount:amount}),
        dataType: 'json',
        cache: false,
         timeout: 600000,
success:function(response)
{
console.log(response);
i	f(response.status=="created")
{
var options = {
    "key": 'rzp_test_X2hkZZ4o0cL8ht',            // Enter the Key ID generated from the Dashboard
    "amount": response.amount,                   // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise
    "currency": "INR",
    "name": "Diary product",
    "description": "payment to member",
    "image": "https://example.com/your_logo",
    "order_id": response.id,
      handler: function (response){
        console.log(response.razorpay_payment_id)
        console.log(response.razorpay_order_id)
        console.log(response.razorpay_signature)
        alert("payment successfull")
    },
  prefill: {
        "name": "",
        "email": "",
        "contact": ""
    },
 notes: {
        "address": "learn code with sid"
    },
     theme: {
        "color": "#3399cc"
    },
};
var rzp = new Razorpay(options);
rzp.on('payment.failed', function (response){
        console.log(response.error.code);
         console.log(response.error.description);
         console.log(response.error.source);
         console.log(response.error.step);
         console.log(response.error.reason);
         console.log(response.error.metadata.order_id);
         console.log(response.error.metadata.payment_id);
         alert("Oops payment failed!!");
});
rzp.open();
}

},
error:function(error)
{
console.log(error);
},
}
);
}