
//https://www.jq22.com/yanshi18230



function makesvg(percentage, inner_text){
  var inner_text = "";
  var abs_percentage = Math.abs(percentage).toString();
  var percentage_str = percentage.toString();
  var classes = ""

  if(percentage < 0){
    classes = "warning-stroke circle-chart__circle--negative";
  } else if(percentage > 0 && percentage <= 44){
    classes = "success-stroke";
  } else{
    classes = "danger-stroke";
  }

 var svg = '<svg class="circle-chart" viewbox="0 0 33.83098862 33.83098862" xmlns="http://www.w3.org/2000/svg">'
     + '<circle class="circle-chart__background" cx="16.9" cy="16.9" r="15.9" />'
     + '<circle class="circle-chart__circle '+classes+'"'
     + 'stroke-dasharray="'+ abs_percentage+',100"    cx="16.9" cy="16.9" r="15.9" />'
     + '<g class="circle-chart__info">'
     + '   <text class="circle-chart__percent" x="16.9" y="16.9">'+percentage_str/10+'</text>';

  if(inner_text){
    svg += '<text class="circle-chart__subline" x="16.91549431" y="22">'+inner_text+'</text>'
  }
  
  svg += ' </g></svg>';
  
  return svg
}

(function( $ ) {

    $.fn.circlechart = function() {
        this.each(function() {
            //var percentage = $(this).data("percentage");
            var inner_text = $(this).text();
            //console.log(percentage+ " ------" +inner_text );
            $(this).html(makesvg(inner_text, inner_text));
        });
        return this;
    };

}( jQuery ));