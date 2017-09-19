`timescale 1ns / 1ps

module controller( CLOCK, MOD, LEDs_strip, ok);

input CLOCK;
input [1:0] MOD;
output [15:0] LEDs_strip;
output ok;

reg ok = 0;

LED_logic led (
	.ok(ok),
	.LEDs_strip(LEDs_strip)
);

always @(posedge CLOCK) begin
	case(MOD)
		1:
			begin
				# 100 ok = ~ok;
			end
		2:
			begin
				# 50 ok = ~ok;
			end
		3:
			begin
				# 10 ok = ~ok;
			end
		0:
			begin
				ok = 0;
			end
	endcase
end

endmodule
