`timescale 1ns / 1ps
module controller(
input clk,
input rst,
input SW,
input rxd,
output txd,
output [7:0] word,
output recieve_ready
);

//reg [7:0] word;  
//reg recieve_ready;
reg transmit_ready;
reg [103:0] string = "Hello world!\n";
reg counter = 0;
reg connection_status = 0;

recieve uut (
		.rxd(rxd), 
		.clk(clk), 
		.rst(rst), 
		.word(word),
		.recieve_ready(recieve_ready)
);
	
transmit uut1 (
		.word(word), 
		.clk(clk), 
		.rst(rst),
		.connection_status(connection_status),
		.txd(txd),
		.transmit_ready(transmit_ready)
);


always @(posedge clk)
		/*if (SW)
			begin
				// hello world
				if (counter > 103 && counter < 1000)
					begin
						counter = counter + 1;
					end
				else if (ready && counter < 104)
					begin
						//word = string[103:95];
						string = string << 8;
						counter = counter + 1;
					end
				else
					begin
						string = "Hello world!\n";
						counter = 0;
					end
			end
		else */
		// echo
				if(recieve_ready)
					connection_status = 1;
				else if(transmit_ready)
					connection_status = 0;

endmodule
