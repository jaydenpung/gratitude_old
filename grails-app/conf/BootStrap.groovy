import grails.util.Holders
import grails.util.Environment
import org.springframework.web.multipart.commons.CommonsMultipartFile

import com.tkm.Hamper

class BootStrap {

    def grailsApplication
    def imageService

    def init = { servletContext ->
        switch (Environment.current) {
            case Environment.PRODUCTION:
                break
            case Environment.DEVELOPMENT:
                if (Hamper.count() == 0) {
                    createDefaultHampers()
                }
                break
        }
    }
    def destroy = {
        def servletContext = Holders.getServletContext()
        servletContext.getAttribute("executor")?.shutdown()
    }

    def createDefaultHampers() {
        try {
            def uploadRsp

            [
                [
                    name: "Bearing Gifts Hamper",
                    price: new BigDecimal("120.00"),
                    quantity: 20,
                    description: "I come bearing gifts",
                    image: "/bootstrapData/defaultHampers//01.png"
                ],
                [
                    name: "Ladies Delight",
                    price: new BigDecimal("70.00"),
                    quantity: 20,
                    description: "For the ladies",
                    image: "/bootstrapData/defaultHampers/02.png"
                ],
                [
                    name: "Felix Hamper",
                    price: new BigDecimal("119.00"),
                    quantity: 20,
                    description: "Felix is in town!",
                    image: "/bootstrapData/defaultHampers/03.png"
                ],
                [
                    name: "Jinx Delight",
                    price: new BigDecimal("49.90"),
                    quantity: 20,
                    description: "For the jinx",
                    image: "/bootstrapData/defaultHampers/04.png"
                ],
                [
                    name: "Ghink Gifts Hamper",
                    price: new BigDecimal("69.50"),
                    quantity: 20,
                    description: "Just Ghink",
                    image: "/bootstrapData/defaultHampers/05.png"
                ],
                [
                    name: "RKY Delight",
                    price: new BigDecimal("99.00"),
                    quantity: 20,
                    description: "No idea what that means",
                    image: "/bootstrapData/defaultHampers/06.png"
                ],
                [
                    name: "Kiddie Gifts Hamper",
                    price: new BigDecimal("250.00"),
                    quantity: 20,
                    description: "Yup, kids get one too...",
                    image: "/bootstrapData/defaultHampers/07.png"
                ],
                [
                    name: "Men Delight",
                    price: new BigDecimal("140.70"),
                    quantity: 20,
                    description: "Because it fair",
                    image: "/bootstrapData/defaultHampers/08.png"
                ],
                [
                    name: "Ideas Hamper",
                    price: new BigDecimal("70.00"),
                    quantity: 20,
                    description: "I'm out of ideas",
                    image: "/bootstrapData/defaultHampers/09.png"
                ],
                [
                    name: "Luxury Hamper",
                    price: new BigDecimal("120.00"),
                    quantity: 20,
                    description: "Only if you're rich",
                    image: "/bootstrapData/defaultHampers/10.png"
                ],
                [
                    name: "Rock n ROll Hamper",
                    price: new BigDecimal("70.00"),
                    quantity: 20,
                    description: "That's right!",
                    image: "/bootstrapData/defaultHampers/11.png"
                ],
                [
                    name: "Royal Tumble Hamper",
                    price: new BigDecimal("50.00"),
                    quantity: 20,
                    description: "Man it rhymes!",
                    image: "/bootstrapData/defaultHampers/12.png"
                ],
                [
                    name: "The Takedown",
                    price: new BigDecimal("88.00"),
                    quantity: 20,
                    description: "What kind of name is that? Must buy!",
                    image: "/bootstrapData/defaultHampers/13.png"
                ],
                [
                    name: "Roche Gifts Hamper",
                    price: new BigDecimal("39.90"),
                    quantity: 20,
                    description: "The infamous roche",
                    image: "/bootstrapData/defaultHampers/14.png"
                ],
                [
                    name: "Chill Gifts Hamper",
                    price: new BigDecimal("29.90"),
                    quantity: 20,
                    description: "Cheapest in town!",
                    image: "/bootstrapData/defaultHampers/15.png"
                ],
            ].each { data ->
                uploadRsp = imageService.saveImage(data.image)

                if (uploadRsp.errors) {
                    throw new Exception (uploadRsp.errors)
                }

                def hamper = new Hamper(
                    name: data.name,
                    price: data.price,
                    quantity: data.quantity,
                    shortDescription: data.description,
                    description: data.description,
                    image: uploadRsp?.result
                )

                hamper.save(flush:true, failOnError: true)
                log.info("Saved hamper: ${hamper.name}")
            }
        }
        catch (Exception ex) {
            log.error("createDefaultHampers() failed: ${ex.message}", ex)
        }
    }
}
