<template>
    <div class="popup">
        <div class="popup-inner">
            <slot />
        
        <div class="row pt-4">
            <div class="col">
               <button @click="execute()" class="btn btn-grad">Delete</button>
            </div>
            <div class="col">
                <button @click="close()" class="btn  btn-grad">Cancel</button>
            </div>
        </div>
        
        </div>    
    </div>
</template>

<script>
    export default{
        methods:{
            close(){
               this.$emit('close')
            },
            execute(){
                this.$emit('execute-del')
            }
        }
    }
</script>

<style lang="scss" scoped>
.popup{
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 99;

    background-color: rgba(0 ,0 ,0 ,0.2);
    display: flex;
    align-items: center;
    justify-content: center;


    .popup-inner{
        background-color: white;
        padding: 32px;
    }

.ludnica{
    align-items: center;
}

}
</style>