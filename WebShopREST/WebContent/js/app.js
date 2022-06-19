/*
	Ovde definisem komponente
*/ 
const ArtikliForma = { template: '<unos-artikla></unos-artikla>'}
const PrikazArtikala = { template: '<prikaz-artikala></prikaz-artikala>'}
const Register = { template: '<edit-product></edit-product>' }
const Objects = { template: '<products></products>' }
const SearchProduct = {template: '<search></search>' }

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'home', component: Objects},
	    { path: '/product', component: Register}
	  ]
});

var app = new Vue({
	router,
	el: '#main',
	data: {
		userStatus : 'NEREGISTROVAN',
		loginData : { username : '', password : ''},
		registerData: { username: '', password : '', firstName: '', lastName: '', gender: 'MALE', role: 'CUSTOMER', dateOfBirth: ''},
		loggedUser : {username: '', role: '', success: false},
		border: document.getElementById('Username').style.border,
		registerUser : null
	},
	methods: {
		login : function() {
			if (this.loginData.username == ''){
				const el = document.getElementById('Username')
				el.style.border = '1px solid red'
				return
			}
			document.getElementById('Username').style.border = this.border
			if (this.loginData.password == ''){
				const el = document.getElementById('Password')
				el.style.border = '1px solid red'
				return
			}
			document.getElementById('Password').style.border = this.border
			axios
				.post('/WebShopREST/rest/users/login', this.loginData)
				.then(response => {loggedUser = response.data; 
								   if (loggedUser.success == true){
								   	this.userStatus = 'ULOGOVAN'; 
								   	alert(this.userStatus)
								   } else {
								   	alert('Pogresni kredencijali')
								   }
								   })
			
		},
		register : function() {
		    console.log('Date of birth ',this.registerData.dateOfBirth)
			if (this.registerData.username == '' || this.registerData.password == '' || this.registerData.firstName == '' ||
			    this.registerData.lastName == '' || this.registerData.dateOfBirth == ''){
			    alert('Please fill out all of the fields')
				return    
			}
			if (this.registerData.password.length < 8){
				alert('Password must be 8 or more characters long');
				return
			}
			axios
				.post('/WebShopREST/rest/users/registration', this.registerData)
				.then(response => {registerUser = response.data; alert('Uspesna registracija')})
		},
		logout : function() {
			axios
				.post('/WebShopREST/rest/users/logout')
				.then(response => {this.userStatus = 'NEREGISTROVAN'})
		}
	}
});