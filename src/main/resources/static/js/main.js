

$(document).ready(function() {

    $('.nBtn, .table .eBtn').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();
        if(text=='Edit'){ // Getting info and fill them in the correct data to make changes
            $.get(href,function(product,status){
                $('.myForm #id').val(product.id)
                $('.myForm #itemCode').val(product.itemCode)
                $('.myForm #itemName').val(product.itemName)
                $('.myForm #width').val(product.width)
                $('.myForm #length').val(product.length)
                $('.myForm #color').val(product.color)
                $('.myForm #style').val(product.style)
                $('.myForm #type').val(product.type)
            });
            $('.myForm #exampleModal').modal();
        }else if(text == 'Edit Quantity'){
            var hrefID = href.substr(href.lastIndexOf('/') + 1);

        // only show the form with the correct #
            $('.myForm'+hrefID+' #exampleModal').modal();
        }else if(text == 'New Item') { // Creating new item so reset all fields
            $('.myForm #id').val('')
            $('.myForm #itemCode').val('')
            $('.myForm #itemName').val('')
            $('.myForm #width').val('')
            $('.myForm #length').val('')
            $('.myForm #color').val('')
            $('.myForm #style').val('')
            $('.myForm #type').val('')
            $('.myForm #exampleModal').modal(); // trigger the modal

        }
    });

    $('.table .delBtn').on('click',function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        var hrefID = href.substr(href.lastIndexOf('/') + 1);
        $('#deleteFormClient').attr('action', '/mycart/delete/'+ hrefID );
        $('#deleteModal #delReference').attr('href', href);
        $('#deleteModal').modal();



    });
});