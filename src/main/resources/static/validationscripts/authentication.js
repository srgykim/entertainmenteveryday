$(function() {
    $("#authorLoginForm").validate({
        rules: {
            author_id: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            author_id: {
                required: "Please enter the id"
            },
            password: {
                required: "Please enter the password"
            }
        },
        errorElement: "div",
        errorPlacement: function(label, element) {
            label.insertAfter(element);
            label.addClass("error-text");
        },
        wrapper: "span"
    });
});