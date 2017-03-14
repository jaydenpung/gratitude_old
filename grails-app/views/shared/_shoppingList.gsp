<div class="row">
    <div class="col-sm-12 col-md-10 col-md-offset-1">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th class="text-center">Price</th>
                    <th class="text-center">Total</th>
                    <th> </th>
                </tr>
            </thead>
            <tbody>
                <g:if test="${products.isEmpty()}">
                    <td colspan="5" class="tkmCenter">No items in your cart.</td>
                </g:if>
                <g:else>
                    <g:each var="product" in="${products}">
                    <tr>
                        <td class="col-sm-8 col-md-6">
                            <div class="media">
                                <a class="thumbnail pull-left" href="${createLink(controller:'hamper', action: 'view', params: [id: product.id])}"> <img src="${resource(file: product.imagePath)}" height="300" width="400"/> </a>
                                <div class="media-body">
                                    <h4 class="media-heading"><a href="${createLink(controller:'hamper', action: 'view', params: [id: product.id])}">${product.name}</a></h4>
                                    <h5 class="media-heading">${product.shortDescription}</h5>
                                    <span>Stock left: </span><span class="text-success"><strong>${product.quantity}</strong></span>
                                </div>
                            </div>
                        </td>
                        <td class="col-sm-1 col-md-1" style="text-align: center">
                            <g:field type="number" class="cartQuantity" name="cartQuantity" value="${product.cartQuantity}"/>
                        </td>
                        <td class="col-sm-1 col-md-1 text-center text-nowrap">
                            <strong>RM ${product.price}</strong>
                        </td>
                        <td class="col-sm-1 col-md-1 text-center text-nowrap">
                            <strong>RM ${product.totalPrice}</strong>
                        </td>
                        <td class="col-sm-1 col-md-1">
                            <button type="button" class="btn btn-danger" id="removeCartBtn">
                                <span class="glyphicon glyphicon-remove"></span> Remove
                            </button>
                        </td>
                    </tr>
                    </g:each>
                    <%-- SubTotals and Totals --%>
                    <tr>
                        <td>   </td>
                        <td>   </td>
                        <td>   </td>
                        <td><h5>Subtotal</h5></td>
                        <td class="text-right"><h5><strong>RM ${totalAmount}</strong></h5></td>
                    </tr>
                    <tr>
                        <td>   </td>
                        <td>   </td>
                        <td>   </td>
                        <td><h5>Estimated shipping</h5></td>
                        <td class="text-right"><h5><strong>RM 0.00</strong></h5></td>
                    </tr>
                    <tr>
                        <td>   </td>
                        <td>   </td>
                        <td>   </td>
                        <td><h3>Total</h3></td>
                        <td class="text-right"><h3><strong>RM ${totalAmount}</strong></h3></td>
                    </tr>
                    <tr>
                        <td>   </td>
                        <td>   </td>
                        <td>   </td>
                        <td>
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
                        </button></td>
                        <td>
                        <button type="button" class="btn btn-success">
                            Checkout <span class="glyphicon glyphicon-play"></span>
                        </button></td>
                    </tr>
                </g:else>
            </tbody>
        </table>
    </div>
</div>

<script>
    $("#removeCartBtn").click(function() {
        var quantity = $(this).closest("tr")   // Finds the closest row <tr> 
                       .find(".cartQuantity")     // Gets a descendent with class="nr"
                       .text();         // Retrieves the text within <td>
        var hamperId = $(this).closest("tr")
                       .find(".cartQuantity")
                       .text();


        $.ajax({
            url: "${createLink(controller: 'hamper', action: 'removeFromCart')}",
            type: 'POST',
            data: { id: hamperId, quantity: quantity },
            success: function(result) {
                $('#cartModal').modal('show');
            }
        });
    });
</script>
