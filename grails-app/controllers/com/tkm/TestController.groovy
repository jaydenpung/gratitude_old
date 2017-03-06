package com.tkm

class TestController {
    def test() {
        try {
        }
        catch(Exception ex) {
            log.error("test() failed: ${ex.message}", ex)
        }
    }
}
