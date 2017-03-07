<!DOCTYPE html>
<html lang="en">

    <head>
        <meta name="layout" content="main" />
    </head>

    <body>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header"><g:message code="product.create.label" /></h1>
            </div>
        </div>
        <g:render template="/shared/messages"/>
        <form class="form-horizontal" role="form" name="editForm">
            <input type="hidden" name="id" value="${product.id}">
            <div class="panel panel-default">
                <div class="panel-heading"><g:message code="product.create.panel.label"/></div>
                <div class="panel-body">
                    <%-- Name --%>
                    <div class="form-group required">
                        <label for="name" class="col-sm-2 control-label input-sm required"><g:message code="product.name.label" /></label>
                        <div class="col-sm-3">
                            <g:textField class="form-control input-sm" name="name" maxlength="100" value="${product.name}"/>
                        </div>
                    </div>
                    <%-- Description --%>
                    <div class="form-group">
                        <label for="description" class="col-sm-2 control-label input-sm"><g:message code="product.description.label" /></label>
                        <div class="col-sm-3">
                            <g:textField class="form-control input-sm" name="description" maxlength="1000" value="${product.description}"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-actions">
                <g:link controller="product" action="list">
                    <button type="button" class="btn btn-default"><g:message code="default.button.back.label"/></button>
                </g:link>
                <g:field type="button" name="btnUpdate" id="btnUpdate" class="btn btn-default buttons" value="${message(code:'default.button.update.label')}"></g:field>
            </div>
        </form>

        <script>
            $(document).ready(function() {

                $('[name=editForm]').bootstrapValidator({
                    excluded: ':disabled',
                    message: 'This value is not valid',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        name: {
                            validators: {
                                notEmpty: {
                                    message: 'The product name is required and cannot be empty'
                                }
                            }
                        }
                    }
                });

                $(".buttons").off('click').on('click', function() {
                    var validator = $('[name=editForm]').data('bootstrapValidator');
                    validator.validate();
                    if (validator.isValid()) {

                        var defaultError = "${message(code: 'default.ajax.failed.error.message')}"
                        var success = "${createLink(controller:'product', action:'list')}";
                        var id = $(this).attr('id');
                        var url;
                        var data = $('[name=editForm]').serialize();

                        if (id == 'btnUpdate') {
                            url = "${createLink(controller:'product', action:'update')}";
                        }

                        callAjax(url, data, success, defaultError);
                    }
                });
            });
        </script>
    </body>

</html>
