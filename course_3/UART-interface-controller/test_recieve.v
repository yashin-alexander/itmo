`timescale 1ns / 1ps

module test_recieve;

    reg rxd;
    reg clk;
    reg rst;
    wire [7:0] word;
	 wire recieve_ready;

    recieve uut (
        .rxd(rxd), 
        .clk(clk), 
        .rst(rst), 
        .word(word),
		  .recieve_ready(recieve_ready)
    );

    initial begin
        rxd = 1;
        clk = 0;
        rst = 0;
    end
    
    always begin
        # 1 clk = ~clk;
    end
    
    always begin
		  # 1 rxd = 0;
        # 1 rxd = 1;
        # 8 rxd = 0;
    end
    
    always begin
        # 200 rst = 1;
    end
      
endmodule
