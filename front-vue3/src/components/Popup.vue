<template>
    <transition name="fade">
        <div class = "modal" v-show = "open">
            <transition name = "drop-in">
            <div class="modal-inner" v-show="open">
                <div class="modal-content">
                    <slot/>
                    <form>
                        <div class="modal-header">
                            <h5 class="modal-title">Login</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="Username">Username<span class="text-danger">*</span></label>
                                <input type="text" name="username" class="form-control" id="Username" placeholder="Enter Username">
                            </div>

                            <div class="mb-3">
                                <label for="Password">Password<span class="text-danger">*</span></label>
                                <input type="password" name="password" class="form-control" id="Password" placeholder="Enter Password">
                            </div>
                        </div>
                        <div class="modal-footer pt-4">                  
                          <input type="button" class="btn btn-success mx-auto w-100" value = "login">
                          <button type="button" @click="$emit('close')">Close</button>
                        </div>
                    </form>
                    
                </div>
            </div>
            </transition>
        </div>
    </transition>
</template>

<script>
   export default {
        name : 'Login',
        props: {
            open: {
                type: Boolean,
                default: true,
            }
        }
   }
</script>

<style>
*,
::before,
::after {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
  background-color: rgba(0, 0, 0, 0.4);
  z-index: 1;
}

.modal-inner {
  max-width: 500px;
  margin: 2rem auto;
}

.modal-content {
  position: relative;
  background-color: #fff;
  border: 1px solid rgba(0, 0, 0, 0.3);
  background-clip: padding-box;
  border-radius: 0.3rem;
  padding: 1rem;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}

.drop-in-enter-active,
.drop-in-leave-active {
  transition: all 0.3s ease-out;
}

.drop-in-enter-from,
.drop-in-leave-to {
  opacity: 0;
  transform: translate(0, -50px);
}
</style>