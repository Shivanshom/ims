// let table = new DataTable('#myTable');


 


function updateStatus(id) {
    var statusElement = document.getElementById('status' + id);

    var buttonElement = document.getElementById('button' + id);

    if (statusElement.textContent === 'In progress') {
        statusElement.textContent = 'Fulfilled';
        buttonElement.textContent = 'Order received';
    }
}





function extractCookie() {
    const cookieRow = document.cookie.split('; ').find(row => row.startsWith('cookie=='));
    return cookieRow ? cookieRow.split('==')[1] : '';
}

// var role = extractCookie('role');





function fetchPurchaseOrders() {
    const godownId = parseInt(JSON.parse(localStorage.getItem('user')).godownId);
    const cookie = extractCookie();
    var role = JSON.parse(localStorage.getItem('user')).role; // Get the role from the local storage
    fetch('http://localhost:8080/api/getAllPurchaseOrders', {
        headers:{
            "Content-Type": "application/json",
            'Authorization': `Bearer ${cookie}`
        }})
    .then(response => response.json())
    .then(data => {
        // Check the role
        if (role === 'admin') {
            // Proceed as before, no filtering needed
            // Fetch the supplier name for each purchase order
            var supplierPromises = data.map(function(order) {
                return fetch('http://localhost:8080/api/getSupplierBySupplierId/' + order.supplierId, {
                    headers:{
                        "Content-Type": "application/json",
                        'Authorization': `Bearer ${cookie}`
                    }
                })
                .then(response => response.json())
                .then(supplierData => {
                    // Add the supplier name to the order
                    order.supplierName = supplierData.supplierName; // Replace 'name' with the actual property name for the supplier name
                    return order;
                });
            });

            // Wait for all supplier names to be fetched
            Promise.all(supplierPromises)
            .then(purchaseOrders => {
                localStorage.setItem("purchaseOrders",purchaseOrders.length);
                populatePurchaseTable(purchaseOrders);

            });
        } else if (role === 'godownhead') {
            // Filter the purchase orders by the godown id
            var filteredData = data.filter(function(order) {
                // console.log("hello");
                return order.godownId === godownId;
            });

            // Fetch the supplier name for each purchase order
            var supplierPromises = filteredData.map(function(order) {
                return fetch('http://localhost:8080/api/getSupplierBySupplierId/' + order.supplierId, {
                    headers:{
                        "Content-Type": "application/json",
                        'Authorization': `Bearer ${cookie}`
                    }
                })
                .then(response => response.json())
                .then(supplierData => {
                    // Add the supplier name to the order
                    order.supplierName = supplierData.supplierName; // Replace 'name' with the actual property name for the supplier name
                    return order;
                });
            });

            // Wait for all supplier names to be fetched
            Promise.all(supplierPromises)
            .then(purchaseOrders => {
                localStorage.setItem("purchaseOrders",purchaseOrders.length);
                populatePurchaseTable(purchaseOrders);

            });
        } else {
            // Invalid role, handle accordingly
            console.error('Invalid role:', role);
        }

        
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}


// Function to populate the purchase table with the fetched data
function populatePurchaseTable(purchaseOrders) {
    var tableBody = document.querySelector('#tbody');
    // Clear out existing rows
    // tbody.innerHTML = '';

    // Loop over the purchase orders and create a table row for each one
    purchaseOrders.forEach(function(order) {
        var row = document.createElement('tr');

        // Create a table cell for each property of the order
        var idCell = document.createElement('td');
        idCell.textContent = order.purchaseId;
        row.appendChild(idCell);

        var dateCell = document.createElement('td');
        var date_object = new Date(order.purchaseDate);
        var day = String(date_object.getDate()).padStart(2, '0');
        var month = String(date_object.getMonth() + 1).padStart(2, '0'); //January is 0!
        var year = date_object.getFullYear();
        var formatted_date = day + '/' + month + '/' + year;
        dateCell.textContent = formatted_date;
        row.appendChild(dateCell);
        

        var priceCell = document.createElement('td');
        priceCell.textContent = order.totalCostPrice;
        row.appendChild(priceCell);

        var quantityCell = document.createElement('td');
        quantityCell.textContent = order.purchaseQuantity;
        row.appendChild(quantityCell);

        var supplierCell = document.createElement('td');
        supplierCell.textContent = order.supplierName; // Use the supplier name from the order data
        row.appendChild(supplierCell);

        var actionCell = document.createElement('td');
        var button = document.createElement('button');
        button.className='btn'
        button.title = "Order Details"
        var icon = document.createElement('i');
        icon.className = 'fa-regular fa-xl fa-file-lines';
        
        button.appendChild(icon);
        button.onclick = function() {
          location.href = `purchaseDetails.html?id=${order.purchaseId}`;
        };
        actionCell.appendChild(button);
        row.appendChild(actionCell);
        

        

        // Add the row to the table body
        tableBody.appendChild(row);
        
       
    });
      // initialize DataTable
      $('#myTable').DataTable();
}


function conditionalRendering() {
    const role = JSON.parse(localStorage.getItem('user')).role;
    console.log(role)
    // console.log(role);
    if (role === "godownhead") {
        document.getElementById('rolebutton').style.display = 'block';
        

    } else if (role === "admin") {
        document.getElementById('rolebutton').style.display = 'none';
        
       
    }
}



// const toggleButton = document.getElementById('nav-toggle');
// const navLinks = document.getElementById('nav-links');

// toggleButton.addEventListener('click', () => {
//     navLinks.classList.toggle('active')
// })

function checkCookie() {
    var cookies = document.cookie.split(";");
    var cookieExists = false;

    for (var i = 0; i < cookies.length; i++) {
      var cookie = cookies[i].trim();
      if (cookie.indexOf("cookie==") === 0) {
        cookieExists = true;
        break;
      }
    }

    if (cookieExists && localStorage.getItem("user") != null) {
      var expirationDate = new Date(
        document.cookie.replace(
          /(?:(?:^|.;\s)cookie\s*\==\s*([^;]).$)|^.*$/,
          "$1"
        )
      );
      if (expirationDate && expirationDate < new Date()) {
        console.log("Cookie is expired.");
        alert("Your session has expired. Please login again.");
        localStorage.clear();
        window.location.href = "login.html";
      } else {
        document.getElementById("purchasebody").style.display = "block";
      }
    } else {
      window.location.href = "login.html";
      alert("Please login first.");
    }
  }

  

window.onload = ()=>{
    checkCookie()
    fetchPurchaseOrders()
    
};


document.addEventListener("DOMContentLoaded", function() {
    //generate product table
    conditionalRendering();
   
  
    
    
});





 












