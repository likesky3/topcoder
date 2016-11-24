package review.dp;

import java.util.Arrays;

public class ShopPositions {

	public static void main(String[] args) {
		ShopPositions obj = new ShopPositions();
		int[] c = {100, 90, 80, 70, 60, 50, 40, 30, 20, 10, 1, 1, 1, 1, 1};
//		System.out.println(obj.maxProfit2(1, 5, c));
		c = new int[]{
				  30,28,25,15,14,10,5,4,2,
				  50,40,30,28,17,13,8,6,3,
				  45,26,14,14,13,13,2,1,1
				};
		int n = 3;
		int m = 3;
		// optimal solution:[3,0,2]
		System.out.println(obj.maxProfit(n, m, c));
		System.out.println("--------------------");
		System.out.println(obj.maxProfit2(n, m, c));
		
		c = new int[]{932, 923, 891, 855, 820, 774, 763, 761, 756, 665, 641, 492, 397, 386, 385, 379, 360, 350, 319, 296, 292, 272, 261, 234, 223, 185, 171, 171, 169, 156, 148, 147, 132, 116, 93, 85, 69, 63, 63, 60, 58, 55, 49, 46, 46, 38, 29, 28, 26, 24, 22, 21, 21, 21, 19, 16, 15, 14, 13, 12, 11, 11, 10, 9, 9, 9, 9, 7, 7, 6, 6, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 991, 720, 710, 645, 638, 538, 505, 480, 393, 332, 319, 306, 279, 269, 261, 230, 226, 215, 188, 183, 167, 157, 151, 139, 131, 130, 98, 95, 86, 76, 74, 70, 62, 59, 53, 52, 46, 44, 43, 42, 41, 39, 34, 28, 26, 24, 24, 21, 21, 20, 20, 20, 19, 19, 19, 18, 15, 11, 8, 7, 7, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 983, 955, 860, 858, 830, 789, 740, 729, 645, 623, 609, 508, 499, 490, 439, 416, 395, 377, 377, 371, 313, 305, 300, 293, 214, 190, 175, 159, 151, 124, 123, 92, 86, 77, 66, 64, 62, 60, 59, 55, 50, 49, 44, 41, 37, 35, 35, 32, 30, 23, 23, 22, 20, 20, 20, 18, 17, 16, 16, 13, 12, 11, 11, 11, 10, 9, 8, 8, 7, 7, 7, 6, 6, 5, 5, 4, 4, 4, 4, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 816, 724, 680, 678, 664, 657, 656, 636, 410, 394, 382, 366, 361, 354, 319, 305, 302, 298, 283, 234, 183, 173, 165, 153, 139, 131, 131, 120, 116, 115, 93, 93, 90, 89, 87, 84, 84, 77, 72, 66, 63, 61, 60, 58, 57, 55, 55, 47, 42, 40, 36, 34, 34, 24, 22, 21, 20, 20, 19, 18, 13, 12, 12, 12, 7, 7, 7, 6, 5, 5, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 945, 924, 892, 838, 813, 769, 757, 659, 569, 541, 480, 454, 427, 420, 368, 333, 324, 302, 265, 232, 196, 170, 169, 167, 162, 155, 131, 124, 114, 101, 77, 76, 75, 62, 54, 53, 48, 42, 42, 41, 36, 36, 26, 24, 22, 21, 20, 19, 19, 17, 16, 15, 13, 13, 12, 10, 10, 9, 9, 8, 8, 6, 6, 5, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 892, 856, 792, 782, 605, 559, 536, 524, 519, 402, 385, 367, 331, 306, 282, 254, 246, 232, 224, 194, 182, 172, 161, 161, 158, 137, 117, 116, 116, 103, 95, 95, 91, 72, 70, 63, 61, 54, 52, 48, 45, 44, 36, 36, 35, 34, 31, 30, 30, 23, 19, 19, 18, 18, 18, 17, 15, 15, 14, 14, 12, 12, 10, 10, 9, 8, 8, 8, 6, 5, 5, 5, 5, 4, 4, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 938, 898, 859, 854, 802, 792, 768, 659, 646, 554, 549, 523, 427, 344, 330, 328, 286, 274, 232, 221, 216, 210, 210, 148, 147, 146, 123, 107, 107, 91, 90, 89, 82, 71, 67, 67, 67, 56, 34, 31, 30, 27, 26, 24, 23, 23, 21, 21, 18, 16, 16, 13, 13, 10, 9, 9, 9, 9, 8, 8, 7, 7, 7, 7, 7, 6, 6, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 996, 901, 895, 849, 734, 732, 705, 581, 489, 479, 462, 431, 425, 399, 392, 384, 375, 292, 285, 254, 253, 251, 210, 206, 196, 144, 138, 120, 119, 110, 102, 100, 96, 96, 88, 84, 77, 59, 58, 53, 52, 43, 43, 39, 38, 35, 35, 34, 34, 31, 27, 19, 18, 16, 14, 13, 12, 12, 10, 10, 10, 9, 8, 8, 8, 7, 7, 7, 6, 6, 5, 5, 5, 5, 5, 4, 4, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 968, 964, 955, 925, 880, 748, 730, 719, 696, 691, 496, 490, 399, 398, 395, 354, 318, 298, 296, 290, 282, 257, 246, 235, 228, 211, 189, 180, 177, 174, 141, 129, 115, 112, 99, 85, 78, 75, 66, 63, 54, 48, 46, 44, 43, 39, 38, 30, 26, 21, 21, 18, 15, 14, 12, 12, 12, 11, 11, 11, 11, 11, 10, 7, 7, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 973, 891, 881, 827, 819, 801, 764, 540, 525, 478, 462, 448, 393, 363, 356, 339, 306, 291, 269, 236, 232, 224, 185, 185, 178, 178, 173, 148, 145, 118, 113, 96, 82, 64, 59, 56, 55, 54, 41, 38, 37, 35, 32, 32, 32, 29, 26, 26, 23, 17, 17, 15, 13, 11, 10, 10, 9, 8, 8, 7, 7, 6, 6, 6, 5, 5, 5, 5, 5, 5, 5, 5, 4, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 986, 846, 800, 737, 733, 603, 580, 575, 561, 522, 462, 457, 456, 435, 431, 391, 388, 370, 370, 354, 297, 288, 252, 247, 230, 227, 200, 186, 152, 139, 127, 123, 121, 120, 110, 106, 93, 79, 76, 54, 50, 48, 48, 45, 39, 32, 30, 28, 25, 25, 24, 23, 21, 19, 17, 16, 15, 14, 13, 12, 11, 11, 9, 8, 8, 8, 8, 7, 7, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 962, 939, 898, 824, 820, 763, 749, 728, 714, 664, 650, 620, 584, 537, 486, 431, 318, 275, 270, 255, 254, 250, 247, 170, 166, 165, 133, 132, 126, 116, 114, 107, 102, 102, 99, 91, 88, 72, 72, 70, 58, 55, 42, 40, 37, 33, 30, 28, 27, 25, 23, 23, 21, 18, 17, 16, 15, 15, 13, 12, 12, 11, 11, 11, 10, 9, 9, 7, 7, 6, 6, 6, 5, 4, 4, 4, 4, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 977, 899, 878, 770, 707, 679, 663, 605, 604, 565, 533, 386, 377, 372, 360, 310, 288, 253, 208, 208, 206, 197, 168, 162, 153, 132, 128, 127, 106, 105, 92, 92, 90, 83, 82, 69, 63, 56, 55, 50, 50, 42, 41, 32, 31, 28, 26, 18, 17, 16, 16, 14, 14, 12, 12, 12, 12, 10, 10, 10, 10, 10, 9, 9, 8, 8, 6, 5, 5, 4, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 830, 807, 758, 745, 619, 543, 510, 485, 393, 376, 357, 345, 326, 326, 306, 282, 268, 266, 221, 193, 190, 186, 181, 160, 140, 129, 116, 109, 94, 89, 81, 75, 71, 71, 55, 54, 46, 45, 42, 41, 41, 40, 39, 38, 36, 35, 34, 33, 31, 30, 30, 21, 20, 19, 17, 15, 15, 12, 11, 9, 8, 8, 8, 8, 7, 7, 7, 6, 6, 5, 5, 5, 5, 4, 4, 4, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 931, 890, 876, 873, 844, 812, 811, 748, 647, 576, 504, 461, 438, 304, 284, 272, 261, 255, 249, 235, 221, 203, 180, 167, 155, 151, 139, 137, 111, 100, 88, 87, 84, 83, 78, 76, 76, 74, 74, 72, 69, 59, 53, 49, 41, 40, 39, 39, 36, 31, 28, 25, 25, 23, 23, 23, 21, 16, 16, 14, 14, 14, 12, 11, 10, 10, 9, 8, 8, 8, 8, 7, 6, 6, 6, 5, 5, 4, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 960, 924, 894, 817, 697, 494, 487, 452, 341, 334, 286, 264, 264, 231, 222, 205, 173, 169, 158, 146, 137, 115, 100, 91, 74, 70, 70, 65, 62, 62, 60, 50, 50, 49, 48, 48, 44, 44, 43, 43, 40, 39, 38, 35, 33, 33, 31, 29, 29, 27, 26, 23, 22, 21, 20, 19, 19, 18, 17, 17, 17, 16, 15, 15, 15, 14, 10, 10, 9, 9, 8, 7, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 2, 2, 2, 2, 2, 974, 938, 898, 878, 772, 621, 482, 427, 402, 322, 316, 277, 274, 272, 256, 253, 212, 189, 182, 176, 169, 167, 166, 149, 147, 136, 118, 108, 87, 84, 82, 74, 69, 66, 65, 59, 54, 45, 44, 41, 34, 28, 27, 27, 24, 23, 20, 20, 19, 17, 17, 16, 15, 15, 14, 13, 12, 11, 11, 11, 10, 10, 9, 9, 8, 8, 7, 6, 6, 6, 6, 4, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 895, 840, 773, 669, 586, 534, 503, 498, 480, 437, 429, 425, 404, 372, 317, 306, 300, 276, 273, 268, 240, 219, 202, 201, 190, 183, 125, 121, 118, 93, 89, 83, 74, 72, 72, 67, 66, 61, 61, 57, 53, 52, 51, 50, 48, 47, 41, 41, 36, 30, 28, 21, 20, 20, 20, 19, 19, 18, 17, 16, 16, 15, 15, 15, 14, 12, 11, 10, 10, 8, 7, 6, 6, 5, 5, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 971, 895, 817, 692, 688, 650, 580, 505, 418, 408, 378, 368, 349, 340, 287, 282, 260, 252, 238, 234, 229, 213, 197, 191, 182, 175, 175, 169, 166, 166, 150, 140, 138, 137, 113, 87, 75, 69, 67, 64, 63, 63, 61, 56, 49, 46, 41, 39, 37, 36, 31, 30, 29, 26, 25, 24, 23, 21, 20, 17, 15, 14, 13, 11, 11, 10, 10, 9, 9, 8, 8, 8, 7, 6, 5, 5, 5, 4, 4, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 885, 749, 683, 570, 507, 501, 395, 388, 374, 319, 315, 290, 268, 263, 246, 153, 150, 118, 114, 97, 78, 75, 74, 71, 71, 69, 69, 67, 58, 54, 54, 52, 49, 40, 37, 34, 32, 32, 30, 26, 26, 26, 23, 23, 19, 19, 18, 17, 16, 14, 12, 11, 10, 9, 9, 9, 9, 8, 8, 7, 7, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 5, 5, 5, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 897, 773, 649, 602, 538, 502, 499, 483, 482, 442, 424, 375, 342, 310, 306, 279, 271, 211, 211, 209, 204, 188, 177, 127, 121, 119, 105, 99, 96, 77, 73, 73, 72, 67, 66, 65, 54, 50, 42, 41, 41, 39, 33, 31, 31, 30, 30, 28, 28, 27, 27, 26, 26, 25, 23, 19, 14, 14, 14, 13, 11, 10, 9, 8, 7, 7, 7, 7, 6, 6, 6, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 999, 994, 850, 810, 770, 746, 582, 539, 531, 407, 380, 373, 269, 258, 254, 252, 240, 236, 235, 232, 225, 204, 202, 191, 173, 145, 129, 125, 121, 116, 113, 107, 104, 85, 82, 77, 77, 66, 60, 57, 54, 48, 45, 44, 42, 38, 32, 30, 29, 24, 17, 17, 15, 15, 13, 12, 11, 7, 7, 6, 5, 5, 5, 5, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 885, 863, 768, 701, 527, 513, 468, 454, 438, 435, 373, 307, 299, 297, 256, 241, 234, 197, 167, 167, 153, 139, 133, 111, 101, 95, 94, 90, 77, 68, 63, 59, 54, 52, 47, 43, 41, 34, 33, 33, 33, 30, 29, 29, 29, 28, 27, 22, 21, 21, 20, 13, 13, 13, 11, 10, 10, 10, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 959, 742, 691, 604, 476, 459, 431, 428, 424, 381, 375, 371, 352, 322, 306, 246, 229, 221, 211, 197, 190, 170, 166, 153, 153, 150, 132, 124, 109, 102, 94, 84, 83, 79, 74, 68, 58, 50, 50, 49, 48, 45, 42, 35, 34, 32, 30, 29, 28, 27, 24, 19, 19, 19, 18, 17, 15, 14, 13, 12, 11, 10, 10, 10, 9, 9, 8, 8, 8, 7, 7, 7, 7, 5, 5, 5, 4, 4, 4, 4, 4, 4, 3, 2, 2, 2, 2, 2, 2, 2, 929, 803, 766, 761, 754, 714, 593, 557, 555, 445, 438, 377, 258, 252, 245, 238, 234, 224, 199, 194, 173, 161, 154, 135, 133, 126, 125, 107, 74, 70, 66, 64, 60, 59, 57, 55, 50, 44, 43, 37, 35, 27, 27, 26, 26, 23, 22, 19, 18, 16, 14, 14, 14, 11, 10, 10, 10, 10, 10, 8, 7, 7, 7, 6, 6, 5, 5, 5, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 928, 875, 868, 844, 837, 784, 735, 671, 656, 582, 550, 538, 486, 419, 402, 373, 356, 355, 326, 303, 290, 260, 239, 224, 212, 206, 189, 180, 157, 139, 137, 111, 110, 110, 102, 86, 85, 83, 77, 71, 64, 62, 52, 44, 43, 42, 42, 42, 40, 35, 30, 28, 27, 25, 22, 16, 16, 14, 13, 13, 12, 11, 11, 10, 9, 8, 8, 7, 7, 7, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 978, 929, 917, 906, 875, 840, 736, 704, 698, 640, 597, 582, 565, 489, 460, 442, 429, 368, 314, 310, 268, 261, 244, 220, 201, 172, 171, 169, 133, 105, 99, 95, 92, 92, 81, 80, 80, 66, 60, 48, 43, 41, 40, 29, 27, 25, 21, 19, 18, 16, 13, 13, 12, 12, 11, 10, 10, 9, 8, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 6, 6, 5, 5, 4, 4, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 957, 943, 938, 812, 802, 702, 695, 643, 586, 566, 554, 532, 510, 478, 415, 369, 368, 335, 327, 319, 315, 300, 260, 227, 219, 216, 200, 193, 183, 177, 145, 127, 115, 94, 82, 79, 65, 65, 57, 52, 40, 39, 37, 30, 30, 30, 28, 28, 27, 25, 24, 23, 21, 20, 18, 18, 18, 13, 12, 11, 10, 10, 9, 8, 7, 6, 6, 5, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 994, 856, 771, 765, 751, 744, 695, 620, 595, 586, 559, 554, 448, 422, 329, 295, 243, 232, 223, 218, 210, 190, 189, 179, 171, 170, 154, 152, 140, 132, 131, 117, 113, 110, 104, 102, 74, 68, 66, 64, 61, 61, 50, 33, 27, 24, 20, 19, 19, 14, 12, 11, 10, 10, 10, 10, 8, 8, 8, 6, 6, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 925, 858, 764, 709, 693, 529, 504, 496, 481, 468, 467, 380, 377, 349, 336, 290, 283, 274, 207, 199, 193, 188, 178, 172, 162, 157, 151, 145, 145, 136, 131, 130, 128, 99, 92, 83, 74, 74, 68, 68, 66, 59, 49, 49, 48, 48, 46, 36, 36, 30, 29, 27, 25, 24, 24, 19, 18, 15, 15, 13, 11, 10, 9, 9, 9, 9, 8, 8, 7, 7, 7, 6, 5, 5, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
		n = 30;
		m = 30;
		System.out.println(obj.maxProfit(n, m, c));
		System.out.println("--------------------");
		System.out.println(obj.maxProfit2(n, m, c));

	}
	
	public int maxProfit(int n, int m, int[] c) {
		if (n == 1) {
			int res = 0;
			for (int i = 1; i <= m; i++) {
				res = Math.max(res, i * c[i - 1]);
			}
			return res;
		}
		// n >= 2
		// prepare information
		this.n = n;
		this.m = m;
		this.C = c;
		dp = new int[31][31][31];
		for (int[][] a2 : dp) {
			for (int[] a1 : a2) {
				Arrays.fill(a1, -1);
			}
		}
		// start dp
		int result = 0;
		for (int a = 0; a <= m; a++) {
			for (int b = 0; b <= m; b++) {
				int p = 0;
				if (a > 0) {
					p = a * c[a + b - 1];
				}
				p += f(2, a, b);
				result = Math.max(result, p);
//				System.out.printf("(%d,%d)=%d\n", a, b, p);
			}
		}
		return result;
	}
	private int n, m;
	private int[] C;
	private int[][][] dp;
	private int f(int i, int a, int b) {
		if (dp[i][a][b] != -1)
			return dp[i][a][b];
		int p = 0;
		if (i == n) {
			p = b * C[(i - 1) * 3 * m + (a + b) - 1];
		} else {
			for (int c = 0; c <= m; c++) {
				p = Math.max(p, b * C[(i - 1) * 3 * m + (a + b + c) - 1] + f(i + 1, b, c));
			}
		}
//		System.out.printf("i=%d, a=%d, b=%d, p=%d\n", i, a, b, p);
		return dp[i][a][b] = p;
	}
	
	public int maxProfit2(int n, int m, int[] C) {
		if (n == 1) {
			int result = 0;
			for (int i = 1; i <= m; i++) {
				result = Math.max(result, i * C[i - 1]);
			}
			return result;
		}
		// dp[i][b][c]: max profit of the first i buildings with the (i - 1)th and i-th building having b and c shops
		int[][][] dp = new int[n + 1][m + 1][m + 1];
		// process i = 1
		for (int b = 0; b <= m; b++) { // b should start from 0, not 1, although when b = 0, we get non profit from building 0, but it may enable us get more with the left buildings
			for (int c = 0; c <= m; c++) {
				if (b + c >= 1) { 
					dp[1][b][c] = b * C[(b + c) - 1];
				}
//					System.out.printf(" dp[1][%d][%d]=%d\n", b, c, dp[1][b][c]);
			}
		}
		// process i from 2 to n - 1
		for (int i = 2; i < n; i++) {
			for (int b = 0; b <= m; b++) {
				for (int c = 0; c <= m; c++) {
					for (int a = 0; a <= m; a++) {
						dp[i][b][c] = Math.max(dp[i - 1][a][b] + b * C[(i - 1) * 3 * m + (a + b + c) - 1], dp[i][b][c]);
					}
//					System.out.printf("dp[%d][%d][%d]=%d\n", i, b, c, dp[i][b][c]);
				}
			}
		}
		// process i = n
		int result = 0;
		for (int b = 0; b <= m; b++) {
			for (int a = 0; a <= m; a++) {
				dp[n][b][0] = Math.max(dp[n - 1][a][b] + b * C[(n - 1) * 3 * m + (a + b) - 1], dp[n][b][0]);
			}
			result = Math.max(dp[n][b][0], result);
//			System.out.printf("dp[%d][%d][0]=%d\n", n, b, dp[n][b][0]);
		}
		return result;
	}
}