function hanldeQuantity (button, id) {
	var inputQuantity = document.getElementById("quantity-inp_"+id);
	var subTotal = document.getElementById("subTotal");
	
    if (button.classList.contains("btn-plus")) {
        var newVal = parseFloat(inputQuantity.value) + 1;
    } else {
        if (parseFloat(inputQuantity.value) > 1) {
            var newVal = parseFloat(inputQuantity.value) - 1;
        } else {
            newVal = 1;
        }
    }
    inputQuantity.value = newVal;
    if(id != null) {
	    var valuePrice = document.getElementById("price_"+id).innerHTML;
	    var totalPriceElem = document.getElementById("total-price_"+id);
	    var total = newVal*parseFloat(valuePrice);
	    totalPriceElem.innerHTML = total+"$";
	    
		var oldSubTotal = subTotal.innerHTML; 
	    if(button.classList.contains("btn-plus")) {
		     subTotal.innerHTML = (parseFloat(oldSubTotal) + parseFloat(valuePrice)) + "$";
		} else if( parseFloat(inputQuantity.value) > 1) {
		     subTotal.innerHTML = (parseFloat(oldSubTotal) - parseFloat(valuePrice)) + "$";
		}
		
	    
	    fetch('http://localhost:8080/Vegetable/update/order-detail/'+id+"/"+newVal)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok ' + response.statusText);
                    console.log(response)
                }
                return response.text();
            })
            .then(data => {
                console.log(data);
                document.getElementById("subTotal").innerHTML = "Cart Total: $" + data.subTotal;
            })
            .catch(error => {
                console.log(error);
            });
            
            
            
            
	}
}