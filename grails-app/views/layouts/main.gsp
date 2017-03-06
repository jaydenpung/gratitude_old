<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
    <head>
        <asset:stylesheet src="bootstrap.min.css"/>
        <asset:stylesheet src="shop-homepage.css"/>
        <asset:stylesheet src="tkm.css"/>
        <asset:stylesheet src="bootstrapValidator.min.css"/>
        <asset:stylesheet src="datatables.min.css"/>

        <asset:javascript src="tkm.js"/>
        <asset:javascript src="jquery.js"/>
        <asset:javascript src="bootstrap.min.js"/>
        <asset:javascript src="datatables.min.js"/>
        <asset:javascript src="bootstrapValidator.min.js"/>
    </head>
    <body>
        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <g:link class="navbar-brand" controller="dashboard" action="index"><g:message code="menuItem.home.label" /></g:link>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li>
                            <g:link controller="product" action="list"><g:message code="menuItem.productMaintenance.label" /></g:link>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>

        <div class="container-fluid main">
            <div class="row">
                <div class="col-md-10 col-lg-10 col-sm-12 col-xs-12">
                    <div class="auto-overflow">
                        <div class="container">
                            <div class="row">

                                <!-- Side Bar -->
                                <div class="col-md-3">
                                    <p class="lead">Shop Name</p>
                                    <div class="list-group">
                                        <a href="#" class="list-group-item">Category 1</a>
                                        <a href="#" class="list-group-item">Category 2</a>
                                        <a href="#" class="list-group-item">Category 3</a>
                                    </div>
                                </div>
                                <!-- Page Content -->
                                <div class="col-md-9">
                                    <g:layoutBody />
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- /.container -->
        <div class="container">
            <hr>
            <!-- Footer -->
            <footer>
                <div class="row">
                    <div class="col-lg-12">
                        <p>Copyright &copy; Your Website 2014</p>
                    </div>
                </div>
            </footer>
        </div>
        <!-- /.container -->
    </body>
</html>
