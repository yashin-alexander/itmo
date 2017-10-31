`timescale 1ns / 1ps

module test_transmit;

    reg [7:0] word;
    reg clk;
    reg rst;

    wire txd;

    transmit uut (
        .word(word), 
        .clk(clk), 
        .rst(rst), 
        .txd(txd)
    );

    initial begin
        word = 129;
        clk = 0;
        rst = 0;
    end
    
    always begin
        # 1 clk = ~clk;
   end
    
    always begin
        # 100 word = 0;
   end
endmodule

