function callAjax(url, data, success, defaultError) {
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        success: function(result) {
            if (result.status == true) {
                window.location.replace(success);
            }
            else {
                // show error div
                $('#errorMsg').html(result.errorMessage);
                $('#errorMsg').fadeIn('fast');
            }
        },
        error: function(result) {
            alert(defaultError);
        }
    });
}

/**
 * Toggle buttons based on the checked status the specified checkBoxes.
 *
 * buttons    - selector string for buttons to enable if any checkBoxes are checked;
                 If all the checkBoxes are unchecked, they will be disabled instead.
 * checkBoxes - selector string for the checkBoxes
 */
function toggleButtonsBasedOnCheckBoxes(buttons, checkBoxes) {
    var anyChecked = $(checkBoxes + ":checked").length > 0;
    if (anyChecked) {
      $(buttons).removeAttr('disabled');
    }
    else {
      $(buttons).attr('disabled','disabled');
    }
}

/**
 * Adds Select All/None functionality to checkboxes

 * checkBoxAll - selector string for the "Select All" checkbox
 * checkBoxes  - selector string for all the checkboxes except "Select All"
 * buttons     - selector string for buttons to enable if any checkBoxes are checked;
                 If all the checkBoxes are unchecked, they will be disabled instead.
 */
function selectAllCheckBox(checkBoxAll, checkBoxes, buttons) {
    // "All" checkbox changed
    $(checkBoxAll).change(function() {
        var checked = $(this).prop('checked');
        $(checkBoxes).prop('checked', checked);
        toggleButtonsBasedOnCheckBoxes(buttons, checkBoxes);
    });
    $('body').on('change', checkBoxes, function () {
        toggleButtonsBasedOnCheckBoxes(buttons, checkBoxes);
        if (!this.checked) {
            $(checkBoxAll).prop('checked', false);
        }
    });
}
