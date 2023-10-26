---
layout: default.md
title: "Sean Koh's Project Portfolio Page"
---

# Project: JABPRO

## Overview
JABPro aims to solve the problem of HR managers having to sort through tons of job applications.

It makes their life easier by allowing them to easily fetch important info about job applicants such as their contact details, application status etc. It serves as a one-stop addressbook for job applications.

## Summary of Contributions
**Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=sk2001git&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=sk2001git&tabRepo=AY2324S1-CS2103T-W09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

**Enhancement implemented**:
* Remark command

**Contribution to the UG**:
* Updated UG for the `remark' command

**Contribution to DG**:
* Contributed to DG for writing in User Stories for week 7
* Contributed to DG for non-functional requirements
* Contributed to DG for glossary
* Contributed to DG for use cases regarding add and remark features

**Contribution to team-based tasks**:
* Keeping track of deadlines and objectives
* Seperating tasks into workable units for team members each week

**Review/mentoring contributions**:
* Generally main reviewer for PR contributions, requests changes when necessary and not always approving

**Contributions beyond the project team**:
*

### Contributions to Developer Guide(Extracts)


### Contributions to User Guide(Extracts)

Adding a remark to a person: `remark`

Edits a remark to an existing person to the address book

Format: `remark INDEX r/REMARK`

* Edits the remark for the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* The previous remark is not saved, and instead is replaced by the inputted remark. The command does not add to the existing remark
* You can empty out a remark by inputting an empty string

Examples:
*  `remark 1 r/Great attitude, hardworking` Edits the remark of the 1st person on the list to have a remark `Great attitude, hardworking`
*  `remark 1 r/` Empties the remark of the 1st person.


**Remark** | `remark r/REMARK` <br> e.g., `remark 1 r/Great attitude, hardworking`
