`timescale 1ns / 1ps
`define MOD0 0
`define MOD1 1000
`define MOD2 500
`define MOD3 200

module controller( SCLK, SW, LD, mode_clock);

input SCLK;
input [1:0] SW;
output [15:0] LD;
output mode_clock;

reg mode_clock = 0;

LED_logic led (
	.mode_clock(mode_clock),
	.LD(LD)
);

always @(posedge SCLK) begin
	case(SW)
		1:
			begin
				# `MOD1;
				mode_clock = ~mode_clock;
			end
		2:
			begin
				# `MOD2; 
				mode_clock = ~mode_clock;
			end
		3:
			begin
				# `MOD3; 
				mode_clock = ~mode_clock;
			end
		0:
			begin
				# `MOD0; 
				mode_clock = 0;
			end
	endcase
end

endmodule
