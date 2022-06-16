import Vue from 'vue'

Vue.Component("unos-artikla", {
    data: function(){
        return{
            artikal: {id: null, name: null, type: null, price:null, quantity:null, available:false}
        }
    },
    template:
    `
    <div>
    <form>
    <table>
        <tr>
            <td>Sifra </td>
            <td><input type = "text" v-model ="artikal.id" name = "name"></td>
        </tr>
        <tr>
            <td>Naziv </td>
            <td><input type = "text" v-model ="artikal.name" name = "name"></td>
        </tr>
        <tr>
            <td>Tip artikla</td>
            <td>
                <select v-model = "artikal.type" name = "type">
                    <option value = "Odeca">ODECA</option>
                    <option value = "Namestaj">NAMESTAJ</option>
                    <option value = "Tehnika">TEHNIKA</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Cena</td>
            <td><input type = "text" v-model="artikal.price" name = "price"></td>
        </tr>
        <tr>
            <td>Kolicina</td>
            <td><input type = "text" v-model="artikal.quantity" name = "quantity"></td>
        </tr>
        <tr>
            <td>Na stanju</td>
            <td><input type = "checkbox" v-model="artikal.available" name = "checkbox"></td>
        </tr>
        <tr>
            <td><button v-on:click = "create">Dodaj</button></td>
        </tr>
    </table>
    </form>
    </div>
    `,
    methods : {
        create : function() {
            axios
                .post('rest/artikli', this.artikal)
                .then((response) => (router.push('/prikazArtikala')))
                .catch(function(error) {
                    if (error.response){
                        toast(error.response.message)
                    }
                })
        }
    },
    mounted() {
        this.artikal = {id: null, name: null, type: null, price:null, quantity:null, available:false}
    }

});