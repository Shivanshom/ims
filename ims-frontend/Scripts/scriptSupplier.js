// let table = new DataTable('#myTable');


// // Initialize DataTable
// $(document).ready(function () {
//     $('#myTable').DataTable();

// });



// Handle navigation item clicks
function onNavItemClick(itemId, url) {
    if (url) {
        window.location.href = url;
    }
}

function extractCookie() {
    const cookieRow = document.cookie.split('; ').find(row => row.startsWith('cookie=='));
    return cookieRow ? cookieRow.split('==')[1] : '';
}

const godownId = parseInt(JSON.parse(localStorage.getItem('user')).godownId);
const cookie = extractCookie();

function refreshTable() {
    fetch('http://localhost:8080/api/getAllSuppliers', {
        headers: {
            "Content-Type": "application/json",
            'Authorization': `Bearer ${cookie}`
        }
    })
    

        .then(response => response.json())

        .then(data => {
            // console.log(data)
            var tbody = document.querySelector('#tbody');

            // // Clear out existing rows
            // tbody.innerHTML = '';

            // Add new rows for each item in the returned data
            data.forEach(item => {
                let row = tbody.insertRow();
                let cell1 = row.insertCell(0);
                let cell2 = row.insertCell(1);
                let cell3 = row.insertCell(2);
                let cell4 = row.insertCell(3);
                let cell5 = row.insertCell(4);
                cell1.textContent = item.supplierId;
                cell2.textContent = item.supplierName;
                cell3.textContent = item.address;
                cell4.textContent = item.contactNumber;
                cell5.innerHTML = `<a class=" btn-info1" href="updatesupplier.html?id=${item.supplierId}">Update</a>`

            });
            $('#myTable').DataTable();


        });
        // $('#myTable').DataTable().destroy();

        

    
}
// initialize DataTable

// const toggleButton = document.getElementById('nav-toggle');
// const navLinks = document.getElementById('nav-links');

// toggleButton.addEventListener('click', () => {
//     navLinks.classList.toggle('active');
//     console.log(navLinks);

// })


// Call refreshTable when the page loads and after updating a supplier

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
        document.getElementById("supplierbody").style.display = "block";
      }
    } else {
      window.location.href = "login.html";
      alert("Please login first.");
    }
  }

  

window.onload = ()=>{
    checkCookie()
    refreshTable()
   
};

    
document.querySelector('form').addEventListener('submit', () => {
    handleFormSubmit();
    refreshTable();
});











