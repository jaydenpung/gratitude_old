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
        <form class="form-horizontal" role="form" name="addForm">
            <div class="panel panel-default">
                <div class="panel-heading"><g:message code="product.create.panel.label"/></div>
                <div class="panel-body">
                    <%-- Name --%>
                    <div class="form-group required">
                        <label for="name" class="col-sm-2 control-label input-sm required"><g:message code="product.name.label" /></label>
                        <div class="col-sm-3">
                            <g:textField class="form-control input-sm" name="name" maxlength="100"/>
                        </div>
                    </div>
                    <%-- Description --%>
                    <div class="form-group">
                        <label for="description" class="col-sm-2 control-label input-sm"><g:message code="product.description.label" /></label>
                        <div class="col-sm-3">
                            <g:textField class="form-control input-sm" name="description" maxlength="1000"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-actions">
                <g:link controller="product" action="list">
                    <button type="button" class="btn btn-default"><g:message code="default.button.back.label"/></button>
                </g:link>
                <g:field type="button" name="btnSave" id="btnSave" class="btn btn-default buttons" value="${message(code:'default.button.save.label')}"></g:field>
            </div>
        </form>

        <script>
            $(document).ready(function() {

                $('[name=addForm]').bootstrapValidator({
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
                    var validator = $('[name=addForm]').data('bootstrapValidator');
                    validator.validate();
                    if (validator.isValid()) {

                        var defaultError = "${message(code: 'default.ajax.failed.error.message')}"
                        var success = "${createLink(controller:'product', action:'list')}";
                        var id = $(this).attr('id');
                        var url;
                        var data = $('[name=addForm]').serialize();

                        if (id == 'btnSave') {
                            url = "${createLink(controller:'product', action:'save')}";
                        }

                        callAjax(url, data, success, defaultError);
                    }
                });
            });
        </script>
    </body>

</html>
