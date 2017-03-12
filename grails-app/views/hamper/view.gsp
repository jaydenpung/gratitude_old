<!DOCTYPE html>
<html lang="en">

    <head>
        <meta name="layout" content="main" />
    </head>

    <body>
        <asset:stylesheet src="viewHamper.css"/>

        <g:render template="/shared/messages"/>
        <div class="card">
            <div class="container-fluid">
                <div class="wrapper row">
                    <div class="preview col-md-6">

                        <div class="preview-pic tab-content">
                            <div class="tab-pane active" id="pic-1">
                                <g:if test="${hamper.image}">
                                    <img src="${resource(file: hamper.image.path)}"/>
                                </g:if>
                                <g:else>
                                    <asset:image src="NoPicAvailable.png"/>
                                </g:else>
                            </div>
                          <div class="tab-pane" id="pic-2"><img src="http://placekitten.com/400/252" /></div>
                          <div class="tab-pane" id="pic-3"><img src="http://placekitten.com/400/252" /></div>
                          <div class="tab-pane" id="pic-4"><img src="http://placekitten.com/400/252" /></div>
                          <div class="tab-pane" id="pic-5"><img src="http://placekitten.com/400/252" /></div>
                        </div>
                        <ul class="preview-thumbnail nav nav-tabs">
                          <li class="active"><a data-target="#pic-1" data-toggle="tab">
                            <g:if test="${hamper.image}">
                                <img src="${resource(file: hamper.image.path)}"/>
                            </g:if>
                            <g:else>
                                <asset:image src="NoPicAvailable.png"/>
                            </g:else>
                          </a></li>
                          <li><a data-target="#pic-2" data-toggle="tab"><img src="http://placekitten.com/200/126" /></a></li>
                          <li><a data-target="#pic-3" data-toggle="tab"><img src="http://placekitten.com/200/126" /></a></li>
                          <li><a data-target="#pic-4" data-toggle="tab"><img src="http://placekitten.com/200/126" /></a></li>
                          <li><a data-target="#pic-5" data-toggle="tab"><img src="http://placekitten.com/200/126" /></a></li>
                        </ul>

                    </div>
                    <div class="details col-md-6">
                        <h3 class="product-title">${hamper.name}</h3>
                        <p class="product-description">${hamper.shortDescription}</p>
                        <h4 class="price">current price: <span>RM ${hamper.price}</span></h4>
                        <h5 class="sizes">sizes:
                            <span class="size" data-toggle="tooltip" title="small">s</span>
                            <span class="size" data-toggle="tooltip" title="medium">m</span>
                            <span class="size" data-toggle="tooltip" title="large">l</span>
                            <span class="size" data-toggle="tooltip" title="xtra large">xl</span>
                        </h5>
                        <h5 class="colors">colors:
                            <span class="color orange not-available" data-toggle="tooltip" title="Not In store"></span>
                            <span class="color green"></span>
                            <span class="color blue"></span>
                        </h5>
                        <div class="action">
                            <button class="add-to-cart btn btn-default" type="button">add to cart</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container-fluid">
                ${raw(hamper.description)}
            </div>
    </div>

    </body>

</html>
