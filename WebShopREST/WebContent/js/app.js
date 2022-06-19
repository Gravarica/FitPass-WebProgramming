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
		registerUser : null
	},
	methods: {
		login : function() {
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