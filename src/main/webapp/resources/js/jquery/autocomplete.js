$(document).ready(function() {
    //attach autocomplete
    $("#tagQuery").autocomplete({
        minLength: 2,
        delay: 500,
        //define callback to format results
        source: function (request, response) {
            $.getJSON("/getProducts", request, function(result) {
                response($.map(result, function(item) {
                    return {
                        label: item.name,
                        value: item.name
                    }
                }));
            });
        },

        //define select handler
        select : function(event, ui) {
            if (ui.item) {
                event.preventDefault();
                $("#selected_tags span").append('<a href=" + ui.item.tag_url + " target="_blank">'+ ui.item.label +'</a>');
                //$("#tagQuery").value = $("#tagQuery").defaultValue
                var defValue = $("#tagQuery").prop('defaultValue');
                $("#tagQuery").val(defValue);
                $("#tagQuery").blur();
                return false;
            }
        }
    });
});