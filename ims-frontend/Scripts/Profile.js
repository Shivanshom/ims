function capitalizeFirstLetter(str) {
    return str.toLowerCase().replace(/(^|\s)\S/g, function (match) {
      return match.toUpperCase();
    });
  }

  function extractCookie() {
    const cookieRow = document.cookie.split('; ').find(row => row.startsWith('cookie=='));
    return cookieRow ? cookieRow.split('==')[1] : '';
}


function getUserData() {
    const user = localStorage.getItem('user');
    const userData = JSON.parse(user);
    const godownHeadId = parseInt(userData.godownHeadId);
    const cookie = extractCookie();
    
    axios.get(`http://localhost:8080/api/getGodownHead/${godownHeadId}`, {
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${cookie}`
            
        }
    })
    .then( response => {

        const userData = response.data;
        const userDataWithoutPassword = { ...userData };
        delete userDataWithoutPassword.password;
        

        localStorage.setItem('userData', JSON.stringify(userDataWithoutPassword));

        document.getElementById('name1').innerText = capitalizeFirstLetter(userData.godownHeadName);
        document.getElementById('designation1').innerText = capitalizeFirstLetter(userData.role);
        document.getElementById('email').innerText = userData.email;
        
        document.getElementById('empid').value = userData.godownHeadId;
        document.getElementById('godownId').value = userData.godownId;
        document.getElementById('name2').value = capitalizeFirstLetter(userData.godownHeadName);
        document.getElementById('designation2').value = capitalizeFirstLetter(userData.role);
        document.getElementById('mobileNo').value = userData.godownheadNo;
        document.getElementById('username2').value = userData.username;
        const address = userData.address;
        if (address && address.trim() !== "") {
            document.getElementById('address').value = capitalizeFirstLetter(address);
        }
    })
    .catch( error => {
        console.error('Error fetching user data:', error);
        alert('Error fetching user data');
    });

}

