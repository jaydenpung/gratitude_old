package com.tkm

class TestController {

    def shoppingCartService
    def hamperService

    def test() {
        try {
            def x = shoppingCartService.getItems()
            log.info ">>>>>>>>>>>>>" + x.dump()
        }
        catch(Exception ex) {
            log.error("test() failed: ${ex.message}", ex)
        }
    }
}
