 Vue.component("search-products", {
 		data: function() {
			return {
				title: "Pretraga proizvoda",
				parameter: null,
				products : null
			} 		
 		},
 		
 			template: `
 				<div>
    				<h3> Pretraga proizvoda </h3>
    				<br>
    				<form>
				        <label>Unos parametara: </label>
				        <input type = "text" v-model = "parameter" name = "search">
				        <input type = "submit" v-on:click = "searchProduct">
    				</form>
    				<br>
				    <table border = "1">
				        <tr bgcolor="lightgrey">
				            <th>Naziv</th>
				            <th>Cena</th>
				        </tr>
				            
				        <tr v-for="(p, index) in products">
				            <td>{{p.name}}</td>
				            <td>{{p.price}}</td>
				            <td>
				                <button>Izmeni</button>
				                <button>Obriši</button>
				            </td>
				        </tr>
				    </table>
				</div>
 				`,
 			methods: {
 				searchProduct : function() {
 					axios
 					.get('rest/products/search/' + this.parameter)
 					.then(response => (this.products = response.data))
 				}
 			}
 });