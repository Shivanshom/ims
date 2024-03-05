// Get the supplier ID from the URL
var urlParams = new URLSearchParams(window.location.search);
var supplierId = urlParams.get('id');

function onNavItemClick(itemId, url) {

    // Your existing code...
    
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

// When the page loads, fetch the current data of the supplier
window.onload = function() {
    fetch('http://localhost:8080/api/getSupplierBySupplierId/' + supplierId ,{
        headers:{
            "Content-Type": "application/json",
            'Authorization': `Bearer ${cookie}`
        }})
        .then(response => response.json())
        .then(data => {
            // Pre-fill the form with the current data
            document.querySelector('input[placeholder="Name"]').value = data.supplierName;
            document.querySelector('input[placeholder="Address"]').value = data.address;
            document.querySelector('input[placeholder="Contact Number"]').value = data.contactNumber;
            console.log(data);
        });
};

// Define the handleFormSubmit function
const handleFormSubmit = event => {
    // Prevent the form from submitting normally
    event.preventDefault();

    // Get the updated data from the form
    var supplierName = document.querySelector('input[placeholder="Name"]').value;
    var address = document.querySelector('input[placeholder="Address"]').value;
    var contactNumber = document.querySelector('input[placeholder="Contact Number"]').value;

    // Call the Update API
    fetch('http://localhost:8080/api/updateSuppliers', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${cookie}`
        },
        body: JSON.stringify({
            supplierId:supplierId,
            supplierName: supplierName,
            address: address,
            contactNumber: contactNumber
        })
    })
    .then(response => response.text()) 
    .then(data => {
        // Handle the response...
        console.log(data);
        alert('Supplier updated successfully!');
        window.location.href = 'supplier.html'; // Redirect to the suppliers page
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred while updating the supplier.');
    });
};

// When the form is submitted, call the handleFormSubmit function
document.querySelector('form').addEventListener('submit', handleFormSubmit);


const toggleButton = document.getElementById('nav-toggle');
const navLinks = document.getElementById('nav-links');

toggleButton.addEventListener('click', () => {
    navLinks.classList.toggle('active')
})
