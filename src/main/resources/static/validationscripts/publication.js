$(function() {
    var validator = $("#addarticle").submit(function() {
        // update underlying textarea before submit validation
        tinyMCE.triggerSave();
    }).validate({
        ignore: "",
        rules: {
            publicationDate: {
                required: true
            },
            mainImageUrl: {
                required: true,
                url: true
            },
            title: {
                required: true
            },
            shortTitledId: {
                required: true
            },
            articleExtract: {
                required: true
            },
            articleContent: {
                required: true
            }
        },
        messages: {
            publicationDate: {
                required: "Please select the date"
            },
            mainImageUrl: {
                required: "Please enter the URL",
                url: "Please enter valid URL"
            },
            title: {
                required: "Please enter the title"
            },
            shortTitledId: {
                required: "Please enter the short titled id"
            },
            articleExtract: {
                required: "Please enter the extract"
            },
            articleContent: {
                required: "Please enter the content"
            }
        },
        errorPlacement: function(label, element) {
            label.insertAfter(element.next());
            label.addClass("error-text");
        },
        wrapper: "span"
    });
    validator.focusInvalid = function() {
        // put focus on tinymce on submit validation
        if (this.settings.focusInvalid) {
            try {
                var toFocus = $(this.findLastActive() || this.errorList.length && this.errorList[0].element || []);
                if (toFocus.is("textarea")) {
                    tinyMCE.get(toFocus.attr("id")).focus();
                } else {
                    toFocus.filter(":visible").focus();
                }
            } catch (e) {
                // ignore IE throwing errors when focusing hidden elements
            }
        }
    }
});

$(function() {
    $("#sliderArticlePublicationForm").validate({
        rules: {
            shortTitledId: {
                required: true
            }
        },
        messages: {
            shortTitledId: {
                required: "Please enter the short titled id"
            }
        },
        errorPlacement: function(label, element) {
            label.insertAfter(element);
            label.addClass("error-text");
        },
        wrapper: "span"
    });
});
