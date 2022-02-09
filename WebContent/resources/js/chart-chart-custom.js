'use strict';
$(document).ready(function() {
    // [ bar-chart ] start
    var bar = document.getElementById("chart-bar-1").getContext('2d');
    var theme_g1 = bar.createLinearGradient(0, 300, 0, 0);
    theme_g1.addColorStop(0, '#1de9b6');
    theme_g1.addColorStop(1, '#1dc4e9');
    var data1 = {
        labels: [0, 1, 2, 3, 4, 5, 6, 7, 8],
        datasets: [{
            label: "Users",
            data: [25000, 45000, 74000, 85000, 24000, 36000, 48000, 41000,12000],
            borderColor: theme_g1,
            backgroundColor: theme_g1,
            hoverborderColor: theme_g1,
            hoverBackgroundColor: theme_g1,        
        }]
    };
    var myBarChart = new Chart(bar, {
        type: 'bar',
        data: data1,
        options: {
            barValueSpacing: 10000
        }
    });
    // [ bar-chart ] end

 // [ bar-chart 2] start
    var bar = document.getElementById("chart-bar-2").getContext('2d');
    var theme_g1 = bar.createLinearGradient(0, 300, 0, 0);
    theme_g1.addColorStop(0, '#ff0000');
    theme_g1.addColorStop(1, '#ff0000');
    var data1 = {
        labels: [0, 1, 2, 3, 4, 5, 6, 7, 8],
        datasets: [{
            label: "Clinics",
            data: [25, 45, 74, 85, 24, 36, 14, 41,12],
            borderColor: theme_g1,
            backgroundColor: theme_g1,
            hoverborderColor: theme_g1,
            hoverBackgroundColor: theme_g1,        
        }]
    };
    var myBarChart = new Chart(bar, {
        type: 'bar',
        data: data1,
        options: {
            barValueSpacing: 5
        }
    });
    // [ bar-chart 2] end
});
