import grails.util.Holders
class BootStrap {

    def init = { servletContext ->
    }
    def destroy = {
        def servletContext = Holders.getServletContext()
        servletContext.getAttribute("executor")?.shutdown()
    }
}
