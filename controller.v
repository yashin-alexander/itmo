`timescale 1ns / 1ps
`define MOD0 0
`define MOD1 500
`define MOD2 250
`define MOD3 100

module controller( SCLK, SW, LD, mode_clock);

input SCLK;
input [1:0] SW;
output [15:0] LD;
output mode_clock;

reg [9:0] counter = 0;
reg [1:0] SW_OLD;
reg mode_clock = 0;

LED_logic led (
	.mode_clock(mode_clock),
	.LD(LD)
);

always @(posedge SCLK) begin
	if (SW_OLD != SW) begin 
		counter = 0;
		assign SW_OLD = SW;
	end 
		
	case(SW)
		0:
			mode_clock = `MOD0;
		1:
			begin
				if (counter == `MOD1) 
					begin
						mode_clock = ~mode_clock;
						counter = 0;
					end
				counter = counter + 1;
			end
		2:
			begin
				if (counter == `MOD2) 
					begin
						mode_clock = ~mode_clock;
						counter = 0;
					end
				counter = counter + 1;
			end
		3:
			begin
				if (counter == `MOD3) 
					begin
						mode_clock = ~mode_clock;
						counter = 0;
					end
				counter = counter + 1;
			end
	endcase
end
endmodule
